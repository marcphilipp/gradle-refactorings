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
