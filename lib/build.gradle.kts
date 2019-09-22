plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    compile("com.google.guava:guava:28.0-jre")
    testCompile("org.junit.jupiter:junit-jupiter:5.5.2")
}

tasks.test {
    useJUnitPlatform()
}
