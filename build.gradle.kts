plugins {
    id("org.jetbrains.intellij") version "1.17.3"
    kotlin("jvm") version "1.9.22"
}

group = "io.metaut.benjamin"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.nanohttpd:nanohttpd:2.3.1")
}

kotlin {
    jvmToolchain(17)
}

intellij {
    //Debug Ide settings
    version.set("2024.3.1") // Target Intellij version for the plugin
    type.set("IC") // "IC" for Community Edition and "IU" for Ultimate Edition
}

tasks {
    patchPluginXml {
        sinceBuild.set("243") // Minimum Supported version
        untilBuild.set("249.*") // Maximum Supported Version
    }

    test {
        useJUnitPlatform()
    }
}
