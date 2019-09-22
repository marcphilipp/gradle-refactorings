plugins {
    java
    application
}

repositories {
    jcenter()
}

val integrationTestSourceSet = sourceSets.create("integrationTest") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
}

val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}

configurations.named(integrationTestSourceSet.runtimeOnlyConfigurationName) {
    extendsFrom(configurations.testRuntimeOnly.get())
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

val integrationTest by tasks.registering(Test::class) {
    description = "Runs the integration tests."
    group = "verification"
    testClassesDirs = integrationTestSourceSet.output.classesDirs
    classpath = integrationTestSourceSet.runtimeClasspath
    mustRunAfter(tasks.test)
    jvmArgumentProviders += PathArgumentProvider("main.class.path", sourceSets["main"].runtimeClasspath)
}

class PathArgumentProvider(
        @Input val propertyName: String,
        @Classpath val fileCollection: FileCollection
) : CommandLineArgumentProvider {
    override fun asArguments() =
            listOf("-D${propertyName}=${fileCollection.asPath}")
}

tasks.check {
    dependsOn(integrationTest)
}
