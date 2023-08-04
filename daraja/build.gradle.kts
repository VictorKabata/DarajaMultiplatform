import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val dokkaOutputDir = buildDir.resolve("reports/dokka")

val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

fun Project.get(key: String, defaultValue: String = "Invalid value $key") =
    gradleLocalProperties(rootDir).getProperty(key)?.toString() ?: System.getenv(key)?.toString()
    ?: defaultValue

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

plugins {
    alias(libs.plugins.nativeCocoapod)
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)

    id("maven-publish")
    id("signing")
    alias(libs.plugins.multiplatformSwiftPackage)
    alias(libs.plugins.gradleVersionUpdate)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        publishLibraryVariants("release")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        // podfile = project.file("../appiOS/Podfile")
        framework {
            baseName = "DarajaMultiplatform"
            isStatic = true
        }
    }

    jvm()

    // js()

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(libs.kotlinX.coroutines)

            implementation(Dependencies.ktorCore)
            implementation(Dependencies.ktorContentNegotiation)
            implementation(Dependencies.ktorJson)
            implementation(Dependencies.ktorLogging)

            implementation(Dependencies.ktorCioEngine)

            implementation(Dependencies.kotlinXSerialization)

            implementation(Dependencies.napier)

            implementation(Dependencies.kotlinxDateTime)

            // ToDo: Update to kotlin 1.8.20 which has base64 encoding in stdLib
            implementation(Dependencies.base64Encoding)

            implementation(Dependencies.cache4k)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(TestDependencies.kotlinxCoroutinesTest)
            implementation(TestDependencies.mockative)
            implementation(TestDependencies.ktorMock)
        }

        sourceSets["androidMain"].dependencies {}
        sourceSets["androidUnitTest"].dependencies {}

        sourceSets["iosMain"].dependencies {}
        sourceSets["iosTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {}
        sourceSets["jvmTest"].dependencies {}

        // sourceSets["jsMain"].dependencies {}
        // sourceSets["jsTest"].dependencies {}
    }
}

android {
    namespace = AndroidSdk.namespace
    compileSdk = AndroidSdk.compileSdkVersion

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildTypes {
        getByName("debug") {}

        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf { isNonStable(candidate.version) && !isNonStable(currentVersion) }

    checkForGradleUpdate = true
    gradleReleaseChannel = "current"

    outputFormatter = "html"
    outputDir = "${project.rootDir}/build/reports"
    reportfileName = "dependencies_report"
}

tasks.dokkaHtml.configure {
    outputDirectory.set(dokkaOutputDir)
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}

val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}

kover {
    verify {
        rule {
            name = "Minimal line coverage rate in percents"
            bound { minValue = 60 }
        }
    }
}

afterEvaluate {

    publishing {

        repositories {
            maven {
                name = "Sonatype"
                url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl
                else releasesRepoUrl

                credentials {
                    username = project.get("OSSRH_USERNAME")
                    password = project.get("OSSRH_PASSWORD")
                }
            }
        }

        publications.withType<MavenPublication> {

            artifact(javadocJar)

            pom {
                groupId = Library.groupId
                artifactId = Library.artifactId
                version = project.get("POM_VERSION")

                name.set(project.get("POM_NAME"))
                description.set(project.get("POM_DESCRIPTION"))
                url.set(project.get("POM_URL"))

                developers {
                    developer {
                        id.set(project.get("POM_DEVELOPER_ID"))
                        name.set(project.get("POM_DEVELOPER_NAME"))
                        email.set(project.get("POM_DEVELOPER_EMAIL"))
                    }
                }

                licenses {
                    license {
                        name.set(project.get("POM_LICENSE_NAME"))
                        url.set(project.get("POM_LICENSE_URL"))
                    }
                }

                issueManagement {
                    system.set(project.get("POM_ISSUE_SYSTEM"))
                    url.set(project.get("POM_ISSUE_URL"))
                }

                scm {
                    connection.set(project.get("POM_SCM_CONNECTION"))
                    developerConnection.set(project.get("POM_SCM_DEVELOPER_CONNECTION"))
                    url.set(project.get("POM_SCM_URL"))
                }
            }
        }

        signing {
            val signingKeyId = project.get("SIGNING_ID")
            val signingKeyPassword = project.get("SIGNING_KEY")
            val signingKey = project.get("SIGNING_PASSWORD")

            useInMemoryPgpKeys(
                signingKeyId, signingKeyPassword, signingKey
            )
            sign(publishing.publications)
        }
    }
}

multiplatformSwiftPackage {
    packageName("DarajaMultiplatform")
    zipFileName("DarajaMultiplatform")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("13") }
    }
    outputDirectory(File(rootDir, "swiftpackage"))

    distributionMode { remote("https://github.com/VictorKabata/DarajaMultiplatform") }
}

// Opt-In Experimental ObjCName in Kotlin > 1.8.0
kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

task("testClasses").doLast {
    println("This is a dummy testClasses task")
}
