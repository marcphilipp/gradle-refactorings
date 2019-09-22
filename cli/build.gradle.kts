plugins {
    java
    application
    `integration-test`
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
    integrationTestImplementation("de.sormuras:bartholdy:0.2.3")
}

application {
    mainClassName = "org.example.cli.App"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.register<Checksum>("jarChecksum") {
    inputFile.set(tasks.jar.flatMap { it.archiveFile })
}
