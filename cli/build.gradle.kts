import com.google.common.hash.Hashing
import com.google.common.io.Files

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.google.guava:guava:${Deps.guava}")
    }
}

plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform(project(":platform")))
    implementation(project(":lib"))
    implementation("org.slf4j:slf4j-api")
    runtimeOnly("org.slf4j:slf4j-simple")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClassName = "org.example.cli.App"
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("jarChecksum") {
    dependsOn(tasks.jar)
    val archiveFile = tasks.jar.flatMap { it.archiveFile }
    val checksumFile = archiveFile.map { File("${it.asFile.absolutePath}.sha512") }
    doFirst {
        val hashCode = Files.asByteSource(archiveFile.get().asFile).hash(Hashing.sha512())
        checksumFile.get().writeText(hashCode.toString())
    }
}
