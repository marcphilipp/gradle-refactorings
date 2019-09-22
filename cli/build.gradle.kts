plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":lib"))
    implementation("org.slf4j:slf4j-simple:1.7.28")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
}

application {
    mainClassName = "org.example.cli.App"
}

tasks.test {
    useJUnitPlatform()
}
