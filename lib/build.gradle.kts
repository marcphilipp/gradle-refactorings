plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    api(platform(project(":platform")))
    compile("org.slf4j:slf4j-api")
    compile("com.google.guava:guava")
    testCompile("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
