plugins {
    `java-platform`
}

dependencies {
    constraints {
        api("org.slf4j:slf4j-api:${Deps.slf4j}")
        api("org.slf4j:slf4j-simple:${Deps.slf4j}")
        api("org.junit.jupiter:junit-jupiter:${Deps.junit}")
        api("com.google.guava:guava:${Deps.guava}")
    }
}
