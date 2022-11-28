object BuildPlugins {
    const val androidLibrary = "com.android.library"
    const val multiplatform = "multiplatform"
    const val kotlinXSerialization = "plugin.serialization"
}

object Versions {
    const val kotlinxCoroutines = "1.6.4"
    const val ktor = "1.6.7"
    const val kotlinxDateTime = "0.3.2"
    const val base64Encoding = "1.1.3"
    const val kotlinSerialization = "1.6.10"
    const val kotlinXSerialization = "1.2.2"
    const val napier = "2.4.0"

    const val mockk = "1.12.3"

    const val kotlinxTestResources = "0.2.2"
}

object Dependencies {
    const val kotlinxCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"

    const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktoriOS = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val ktorJvm = "io.ktor:ktor-client-java:${Versions.ktor}"
    const val ktorMock = "io.ktor:ktor-client-mock:${Versions.ktor}"

    const val ktorCioEngine =
        "io.ktor:ktor-client-cio:${Versions.ktor}" // ToDo: Replace with platform specific engines

    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"

    const val base64Encoding =
        "io.matthewnelson.kotlin-components:encoding-base64:${Versions.base64Encoding}"

    const val kotlinXSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinXSerialization}"

    const val napier = "io.github.aakira:napier:${Versions.napier}"
}

object TestDependencies {
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    const val kotlinxTestResources = "com.goncalossilva:resources:${Versions.kotlinxTestResources}"

    const val kotlinxCoroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"
}

object AndroidSdk {
    const val namespace = "com.vickikbt.darajakmp"
    const val minSdkVersion = 16
    const val compileSdkVersion = 31
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.0"
}