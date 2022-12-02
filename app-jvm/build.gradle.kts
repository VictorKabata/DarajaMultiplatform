plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(project(":daraja-common"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}
