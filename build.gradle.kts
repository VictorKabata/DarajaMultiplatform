plugins {
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("multiplatform").version(Versions.kotlin).apply(false)
    id("org.jetbrains.kotlin.jvm") version Versions.kotlin apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.android.application") version "7.3.1" apply false

    id(BuildPlugins.ktLint) version Versions.ktLint
    id(BuildPlugins.detekt) version Versions.detekt
    id(BuildPlugins.spotless) version Versions.spotless

    id(BuildPlugins.kmmbridge) version Versions.kmmbridge apply false
}

subprojects {
    apply(plugin = BuildPlugins.ktLint)
    ktlint {
        debug.set(true)
        verbose.set(true)
        android.set(false)
        outputToConsole.set(true)
        outputColorName.set("RED")
        filter {
            enableExperimentalRules.set(true)
            exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
            include("**/kotlin/**")
        }
    }

    apply(plugin = BuildPlugins.detekt)
    detekt {
        parallel = true
        config = files("${project.rootDir}/config/detekt/detekt.yml")
    }

    apply(plugin = BuildPlugins.spotless)
    spotless {
        kotlin {
            target("**/*.kt")
            licenseHeaderFile(
                rootProject.file("${project.rootDir}/spotless/copyright.kt"),
                "^(package|object|import|interface)"
            )
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
