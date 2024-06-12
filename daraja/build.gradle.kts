import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import java.util.Locale

val dokkaOutputDir = buildDir.resolve("reports/dokka")

val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

fun Project.get(key: String, defaultValue: String = "Invalid value $key") =
    gradleLocalProperties(rootDir).getProperty(key)?.toString() ?: System.getenv(key)?.toString()
        ?: defaultValue

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
        version.uppercase(Locale.getDefault()).contains(it)
    }
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
    alias(libs.plugins.gradleVersionUpdate)

    id("maven-publish")
    id("signing")
    alias(libs.plugins.multiplatformSwiftPackage)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    kotlin.applyDefaultHierarchyTemplate()

    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    jvm()

    // js()

    cocoapods {
        summary = "Daraja API Swift Wrapper built using Kotlin Multiplatform"
        homepage = "https://github.com/VictorKabata/DarajaMultiplatform.git"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "DarajaMultiplatform"
            isStatic = true
        }
    }

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(libs.kotlinX.coroutines)

            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.json)
            implementation(libs.ktor.logging)

            implementation(libs.kotlinX.serializationJson)
            implementation(libs.kotlinX.dateTime)

            implementation(libs.napier)

            implementation(libs.cache4k)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinX.coroutines.test)
            implementation(libs.mockative)
            implementation(libs.ktor.mock)
        }

        sourceSets["androidMain"].dependencies {
            implementation(libs.ktor.android)
        }
        sourceSets["androidUnitTest"].dependencies {}

        sourceSets["iosMain"].dependencies {
            implementation(libs.ktor.darwin)
        }
        sourceSets["iosTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {
            implementation(libs.ktor.java)
        }
        sourceSets["jvmTest"].dependencies {}

        // sourceSets["jsMain"].dependencies {}
        // sourceSets["jsTest"].dependencies {}
    }
}

android {
    defaultConfig {
        minSdk = 21
        compileSdk = 34
    }
    namespace = "com.vickikbt.darajakmp"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    buildTypes {
        getByName("debug") {}

        getByName("release") {
            isMinifyEnabled = true
        }
    }
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    rejectVersionIf { isNonStable(candidate.version) && !isNonStable(currentVersion) }

    checkForGradleUpdate = true
    gradleReleaseChannel = "current"

    outputFormatter = "html"
    outputDir = "${project.rootDir}/daraja/build/reports"
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
    reports {
        verify {
            rule {
                minBound(30)
            }
        }

        filters {
            excludes {
                classes("*BuildConfig")
            }
        }
    }
}

publishing {

    repositories {
        maven {
            name = "Sonatype"
            url = if (version.toString().endsWith("SNAPSHOT")) {
                snapshotsRepoUrl
            } else {
                releasesRepoUrl
            }

            credentials {
                username = project.get("OSSRH_USERNAME")
                password = project.get("OSSRH_PASSWORD")
            }
        }
    }

    publications.withType<MavenPublication> {

        artifact(javadocJar)

        pom {
            groupId = project.get("POM_GROUPID")
            artifactId = project.get("POM_ARTIFACTID")
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

multiplatformSwiftPackage {
    packageName("DarajaMultiplatform")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("14.1") }
    }
}

// Opt-In Experimental ObjCName in Kotlin > 1.8.0
kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}
