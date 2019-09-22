import com.google.common.hash.Hashing
import com.google.common.io.Files
import javax.inject.Inject

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

tasks.register<Checksum>("jarChecksum") {
    inputFile.set(tasks.jar.flatMap { it.archiveFile })
}

open class Checksum @Inject constructor(objects: ObjectFactory) : DefaultTask() {

    @InputFile
    @PathSensitive(PathSensitivity.NONE)
    val inputFile = objects.fileProperty()

    @OutputFile
    val checksumFile = objects.fileProperty().convention {
        File("${inputFile.get().asFile}.sha512")
    }

    @TaskAction
    fun generateChecksum() {
        val hashCode = Files.asByteSource(inputFile.get().asFile).hash(Hashing.sha512())
        checksumFile.get().asFile.writeText(hashCode.toString())
    }
}
