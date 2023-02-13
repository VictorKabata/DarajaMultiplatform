object Library {
    const val groupId = "io.github.victorkabata"
    const val artifactId = "daraja-multiplatform"
}

object BuildPlugins {
    const val androidLibrary = "com.android.library"
    const val multiplatform = "multiplatform"
    const val kotlinXSerialization = "plugin.serialization"
    const val mavenPublish = "maven-publish"
    const val signing = "signing"
    const val kmmbridge = "co.touchlab.faktory.kmmbridge"

    const val ktLint = "org.jlleitschuh.gradle.ktlint"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val gradleVersionUpdates = "com.github.ben-manes.versions"
    const val spotless = "com.diffplug.spotless"
    const val dokka = "org.jetbrains.dokka"
    const val kover = "org.jetbrains.kotlinx.kover"
}

object Versions {
    const val kotlin = "1.8.0"

    const val ktLint = "11.0.0"
    const val detekt = "1.19.0"
    const val gradleVersionUpdate = "0.45.0"
    const val spotless = "6.2.2"
    const val dokka = "1.7.20"
    const val kover = "0.6.1"
    const val kmmbridge = "0.3.4"

    const val kotlinxCoroutines = "1.6.4"
    const val ktor = "2.2.3"
    const val kotlinxDateTime = "0.4.0"
    const val base64Encoding = "1.2.1"
    const val kotlinSerialization = "1.8.10"
    const val kotlinXSerialization = "1.4.1"
    const val napier = "2.6.1"
    const val cache4k = "0.9.0"

    const val mockative = "1.3.1"
    const val mockk = "1.13.4"

    const val kotlinxTestResources = "0.2.4"

    const val composeDesktop = "1.2.0-alpha01-dev683"
}

object Dependencies {
    const val kotlinxCoroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutines}"

    const val ktorCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    const val ktorJson = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"

    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktoriOS = "io.ktor:ktor-client-ios:${Versions.ktor}"
    const val ktorJvm = "io.ktor:ktor-client-java:${Versions.ktor}"

    const val ktorCioEngine =
        "io.ktor:ktor-client-cio:${Versions.ktor}" // ToDo: Replace with platform specific engines

    const val kotlinxDateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDateTime}"

    const val base64Encoding =
        "io.matthewnelson.kotlin-components:encoding-base64:${Versions.base64Encoding}"

    const val kotlinXSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinXSerialization}"

    const val napier = "io.github.aakira:napier:${Versions.napier}"

    const val cache4k = "io.github.reactivecircus.cache4k:cache4k:${Versions.cache4k}"
}

object TestDependencies {
    const val mockative = "io.mockative:mockative:${Versions.mockative}"

    const val ktorMock = "io.ktor:ktor-client-mock:${Versions.ktor}"

    const val kotlinxCoroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutines}"

    const val kotlinxTestResources = "com.goncalossilva:resources:${Versions.kotlinxTestResources}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}

object AndroidSdk {
    const val namespace = "com.vickikbt.darajakmp"
    const val minSdkVersion = 16
    const val compileSdkVersion = 31
    const val targetSdkVersion = compileSdkVersion
    const val versionCode = 1
    const val versionName = "1.0"
}
