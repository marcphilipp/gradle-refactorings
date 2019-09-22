plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    compile("org.slf4j:slf4j-api:1.7.28")
    compile("com.google.guava:guava:28.0-jre")
    testCompile("org.junit.jupiter:junit-jupiter:5.5.2")
}

tasks.test {
    useJUnitPlatform()
}
