import BuildPlugins.maven
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

val dokkaOutputDir = buildDir.resolve("reports/dokka")

val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")

plugins {
    kotlin(BuildPlugins.multiplatform)
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(BuildPlugins.dokka) version Versions.dokka
    id(BuildPlugins.kover) version Versions.kover
    id(BuildPlugins.maven)
}

kotlin {
    android {
        publishLibraryVariants("release", "debug")
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
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            java.srcDir("src/androidMain/kotlin")
        }
    }
    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
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
        publications {

            /*repositories {
                maven {
                    name = "OSS"
                    url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl
                    else releasesRepoUrl

                    credentials{
                        username = "VictorKabata"
                        password = "Some password"
                    }
                }
            }*/

            create<MavenPublication>("maven") {

                artifact(javadocJar)

                from(components["java"])

                pom {
                    groupId = Library.groupId
                    artifactId = Library.artifactId
                    version = Library.version

                    name.set("Daraja Multiplatform")
                    description.set("Kotlin Multiplatform API wrapper for the M-Pesa/Daraja API")
                    url.set("https://github.com/VictorKabata/DarajaMultiplatform")

                    developers {
                        developer {
                            id.set("VictorKabata")
                            name.set("Victor Kabata")
                            email.set("victorbro14@gmail.com")
                        }
                    }

                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }

                    issueManagement {
                        system.set("Github")
                        url.set("https://github.com/VictorKabata/DarajaMultiplatform/issues")
                    }

                    scm {
                        connection.set("https://github.com/VictorKabata/DarajaMultiplatform.git")
                        url.set("https://github.com/VictorKabata/DarajaMultiplatform")
                    }
                }
            }
        }
    }
}
