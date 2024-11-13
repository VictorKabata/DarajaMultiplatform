import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Locale

val dokkaOutputDir = buildDir.resolve("reports/dokka")

val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

fun Project.get(
    key: String,
    defaultValue: String = "Invalid value $key",
) = gradleLocalProperties(rootDir).getProperty(key)?.toString() ?: System.getenv(key)?.toString()
    ?: defaultValue

fun isNonStable(version: String): Boolean {
    val stableKeyword =
        listOf("RELEASE", "FINAL", "GA").any {
            version.uppercase(Locale.getDefault()).contains(it)
        }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kover)
    alias(libs.plugins.gradleVersionUpdate)
    alias(libs.plugins.npmPublish)

    id("maven-publish")
    id("signing")
    alias(libs.plugins.multiplatformSwiftPackage)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    kotlin.applyDefaultHierarchyTemplate()

    androidTarget {
        publishLibraryVariants("debug", "release")

        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "DarajaMultiplatform"
            isStatic = true
        }
    }

    js(IR) {
        moduleName = "DarajaMultiplatform"

        binaries.library()
        nodejs {}
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinX.coroutines)

            implementation(libs.ktor.contentNegotiation)
            implementation(libs.ktor.json)
            implementation(libs.ktor.logging)

            implementation(libs.kotlinX.serializationJson)
            implementation(libs.kotlinX.dateTime)

            implementation(libs.napier)

            implementation(libs.cache4k)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinX.coroutines.test)
            implementation(libs.mockative)
            implementation(libs.ktor.mock)
            implementation(libs.assertK)
        }

        androidMain.dependencies {
            implementation(libs.ktor.android)
        }

        iosMain.dependencies {
            implementation(libs.ktor.darwin)
        }

        jsMain.dependencies {
            implementation(libs.ktor.js)
        }
    }
}

android {
    defaultConfig {
        minSdk = 21
        compileSdk = 34
    }

    namespace = "com.vickbt.darajamultiplatform"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

val javadocJar =
    tasks.register<Jar>("javadocJar") {
        dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
        archiveClassifier.set("javadoc")
        from(dokkaOutputDir)
    }

kover {
    reports {
        verify {
            rule {
                minBound(20)
            }
        }

        filters {
            excludes {
                classes("*BuildConfig")
                annotatedBy("**Generated**")
            }
        }
    }
}

publishing {

    repositories {
        maven {
            name = "Sonatype"
            url =
                if (version.toString().endsWith("SNAPSHOT")) {
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
        groupId = project.get("POM_GROUPID")
        artifactId = project.get("POM_ARTIFACTID")
        version = project.get("POM_VERSION")

        artifact(javadocJar.get())

        pom {
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
            signingKey,
        )
        sign(publishing.publications)
    }
}

multiplatformSwiftPackage {
    packageName("DarajaMultiplatform")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("13") }
    }
}

// Opt-In Experimental ObjCName in Kotlin > 1.8.0
kotlin.sourceSets.all {
    languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
}

// Work around for tasks dependency issue: https://youtrack.jetbrains.com/issue/KT-46466
val dependsOnTasks = mutableListOf<String>()
tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOnTasks.add(this.name.replace("publish", "sign").replaceAfter("Publication", ""))
    dependsOn(dependsOnTasks)
}

npmPublish {
    organization.set("NPM_USERNAME")
    readme.set(File("$rootDir/README.md"))
    packages {
        named("js") {
            packageJson {
                name.set(project.get("POM_ARTIFACTID"))
                version.set(project.get("POM_VERSION"))
                homepage.set(project.get("POM_URL"))
                description.set(project.get("POM_DESCRIPTION"))
                keywords.set(listOf("daraja", "m-pesa", "mpesa", "daraja api", "m-pesa api"))
                license.set(project.get("POM_LICENSE_NAME"))

                author {
                    name.set(project.get("POM_DEVELOPER_NAME"))
                    email.set(project.get("POM_DEVELOPER_EMAIL"))
                }
                repository {
                    type.set("git")
                    url.set(project.get("POM_URL"))
                }
            }
        }
    }
    registries {
        register("npmjs") {
            uri.set("https://registry.npmjs.org")
            authToken.set("NPM_TOKEN")
        }
        // github {}
    }
}
