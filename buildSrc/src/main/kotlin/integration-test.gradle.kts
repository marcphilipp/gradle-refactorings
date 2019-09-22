plugins {
    java
}

val integrationTestSourceSet = sourceSets.create("integrationTest") {
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
}

configurations.named(integrationTestSourceSet.implementationConfigurationName) {
    extendsFrom(configurations.testImplementation.get())
}

configurations.named(integrationTestSourceSet.runtimeOnlyConfigurationName) {
    extendsFrom(configurations.testRuntimeOnly.get())
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
