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
    kotlin(BuildPlugins.multiplatform)
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(BuildPlugins.dokka) version Versions.dokka
    id(BuildPlugins.kover) version Versions.kover
    id(BuildPlugins.mavenPublish)
    id(BuildPlugins.signing)
    // id(BuildPlugins.kmmbridge) version Versions.kmmbridge

    id(BuildPlugins.gradleVersionUpdates) version Versions.gradleVersionUpdate
}

kotlin {
    android {
        publishLibraryVariants("release")
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }
    iosTarget("iOS") {}

    // jvm()

    // js()

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(Dependencies.kotlinxCoroutines)

            implementation(Dependencies.ktorCore)
            implementation(Dependencies.ktorContentNegotiation)
            implementation(Dependencies.ktorJson)
            implementation(Dependencies.ktorLogging)

            implementation(Dependencies.ktorCioEngine)

            implementation(Dependencies.kotlinXSerialization)

            api(Dependencies.napier) // ToDo: Change to implementation

            implementation(Dependencies.kotlinxDateTime)

            implementation(Dependencies.base64Encoding)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(TestDependencies.kotlinxCoroutinesTest)
            implementation(TestDependencies.mockative)
            implementation(TestDependencies.ktorMock)
            implementation(TestDependencies.kotlinxTestResources)
        }

        sourceSets["androidMain"].dependencies {}
        sourceSets["androidTest"].dependencies {}

        sourceSets["iOSMain"].dependencies {}
        sourceSets["iOSTest"].dependencies {}

        // sourceSets["jvmMain"].dependencies {}
        // sourceSets["jvmTest"].dependencies {}

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

    buildTypes {
        getByName("debug") {
        }

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
            bound { minValue = 40 }
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
                signingKeyId,
                signingKeyPassword,
                signingKey
            )
            sign(publishing.publications)
        }
    }
}

/*kmmbridge {
    spm()
    manualVersions()
    versionPrefix.set("9.0")
}*/
