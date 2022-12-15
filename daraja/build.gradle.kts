import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(BuildPlugins.multiplatform)
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinXSerialization) version Versions.kotlinSerialization
    id(BuildPlugins.dokka) version Versions.dokka
    id(BuildPlugins.kover) version Versions.kover
}

kotlin {
    android()

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
    outputDirectory.set(buildDir.resolve("reports/dokka"))
}

kover {
    verify {
        rule {
            name = "Minimal line coverage rate in percents"
            bound { minValue = 80 }
        }
    }
}
