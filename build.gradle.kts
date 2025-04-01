plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //testImplementation(platform("org.junit:junit-bom:5.10.0"))
    //testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.rest-assured:rest-assured:5.5.1") // should be before Junit // hamcrest here
    testImplementation(platform("org.junit:junit-bom:5.12.1")) // took from gradle kotlin
    testImplementation("org.junit.jupiter:junit-jupiter") // took from gradle kotlin
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") // took from gradle kotlin
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.18.3") // java object to json format
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}