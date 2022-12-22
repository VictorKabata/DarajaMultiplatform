import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

val dokkaOutputDir = buildDir.resolve("reports/dokka")

val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

fun Project.get(key: String, defaultValue: String = "$key not found") =
    gradleLocalProperties(rootDir).getProperty(key)?.toString() ?: System.getenv(key)
        ?: defaultValue

plugins {
    kotlin(BuildPlugins.multiplatform)
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(BuildPlugins.dokka) version Versions.dokka
    id(BuildPlugins.kover) version Versions.kover
    id(BuildPlugins.mavenPublish)
    id(BuildPlugins.signing)
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
                version = Library.version

                name.set(get("POM_NAME"))
                description.set(get("POM_DESCRIPTION"))
                url.set(get("POM_URL"))

                developers {
                    developer {
                        id.set(get("POM_DEVELOPER_ID"))
                        name.set(get("POM_DEVELOPER_NAME"))
                        email.set(get("POM_DEVELOPER_EMAIL"))
                    }
                }

                licenses {
                    license {
                        name.set(get("POM_LICENSE_NAME"))
                        url.set(get("POM_LICENSE_URL"))
                    }
                }

                issueManagement {
                    system.set(get("POM_ISSUE_SYSTEM"))
                    url.set(get("POM_ISSUE_URL"))
                }

                scm {
                    connection.set(get("POM_SCM_CONNECTION"))
                    developerConnection.set(get("POM_SCM_DEVELOPER_CONNECTION"))
                    url.set(get("POM_SCM_URL"))
                }
            }
        }

        signing {
            useInMemoryPgpKeys(
                get("SIGNING_ID"),
                get("SIGNING_KEY"),
                get("SIGNING_PASSWORD")
            )
            sign(publishing.publications)
        }
    }
}
