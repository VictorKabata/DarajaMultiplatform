plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    id("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
