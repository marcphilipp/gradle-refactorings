plugins {
    java
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(project(":lib"))
    implementation("org.slf4j:slf4j-api:${Deps.slf4j}")
    runtimeOnly("org.slf4j:slf4j-simple:${Deps.slf4j}")
    testImplementation("org.junit.jupiter:junit-jupiter:${Deps.junit}")
}

application {
    mainClassName = "org.example.cli.App"
}

tasks.test {
    useJUnitPlatform()
}
