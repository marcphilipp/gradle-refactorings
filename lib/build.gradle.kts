plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    api(platform(project(":platform")))
    implementation("org.slf4j:slf4j-api")
    api("com.google.guava:guava")
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
