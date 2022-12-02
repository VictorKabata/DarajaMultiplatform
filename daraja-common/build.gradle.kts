import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(BuildPlugins.multiplatform)
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinXSerialization) version Versions.kotlinSerialization
}

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        System.getenv("NATIVE_ARCH")?.startsWith("arm") == true -> ::iosSimulatorArm64
        else -> ::iosX64
    }
    iosTarget("iOS") {}

    jvm()

    // js()

    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(Dependencies.kotlinxCoroutines)

            implementation(Dependencies.ktorCore)
            implementation(Dependencies.ktorSerialization)
            implementation(Dependencies.ktorLogging)

            implementation(Dependencies.ktorCioEngine)

            implementation(Dependencies.kotlinXSerialization)

            api(Dependencies.napier) //ToDo: Change to implementation

            implementation(Dependencies.kotlinxDateTime)

            implementation(Dependencies.base64Encoding)
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test"))
        }

        sourceSets["androidMain"].dependencies {}
        sourceSets["androidTest"].dependencies {}

        sourceSets["iOSMain"].dependencies {}
        sourceSets["iOSTest"].dependencies {}

        sourceSets["jvmMain"].dependencies {}
        sourceSets["jvmTest"].dependencies {}

        //sourceSets["jsMain"].dependencies {}
        //sourceSets["jsTest"].dependencies {}
    }
}

android {
    namespace = AndroidSdk.namespace
    compileSdk = AndroidSdk.compileSdkVersion
    defaultConfig {
        minSdk = AndroidSdk.minSdkVersion
        targetSdk = AndroidSdk.targetSdkVersion
    }
}