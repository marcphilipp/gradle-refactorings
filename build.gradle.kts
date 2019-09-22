subprojects {
    plugins.withId("java") {
        dependencies {
            add("compile", "org.slf4j:slf4j-api:1.7.28")
        }
    }
}
