plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    compile("org.slf4j:slf4j-api:${Deps.slf4j}")
    compile("com.google.guava:guava:${Deps.guava}")
    testCompile("org.junit.jupiter:junit-jupiter:${Deps.junit}")
}

tasks.test {
    useJUnitPlatform()
}
