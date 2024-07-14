pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://s01.oss.sonatype.org/content/repositories/releases/")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven(uri("https://raw.githubusercontent.com/a-sit-plus/kotlinx.serialization/mvn/repo"))
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
            name = "bigNum"
        }
    }
}

rootProject.name = "DarajaMultiplatform"

include(":daraja")
include(":app-android")
include(":app-desktop")
