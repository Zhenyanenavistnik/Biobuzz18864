import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.repositories

plugins {
    java
}

version = "1.0.0"

repositories {
    maven("https://www.jetbrains.com/intellij-repository/releases")
}

val platformVersion = "242.26775.26"

dependencies {
    for (module in listOf("core", "core-ui", "ide", "ide-core", "util")) {
        compileOnly("com.jetbrains.intellij.platform:$module:$platformVersion") {
            isTransitive = false
        }
    }
}

tasks.compileJava {
    options.release.set(21)
    options.encoding = "UTF-8"
}

tasks.register<Zip>("buildPlugin") {
    archiveFileName.set("RobotField.zip")
    destinationDirectory.set(layout.buildDirectory.dir("distributions"))

    from(tasks.jar) {
        into("RobotField/lib")
    }
}