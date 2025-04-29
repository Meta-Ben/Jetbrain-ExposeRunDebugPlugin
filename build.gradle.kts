plugins {
    id("org.jetbrains.intellij") version "1.17.3"
    kotlin("jvm") version "1.9.22"
}

group = "com.expose_run_debug_plugin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.nanohttpd:nanohttpd:2.3.1")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

intellij {
    version = "2023.1" // Remplace cela par la version d'IntelliJ que tu utilises
    type = "IC" // "IC" pour Community Edition, "IU" pour Ultimate Edition
}

