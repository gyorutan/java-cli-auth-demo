plugins {
    java
//    `java-library`
}

group = "com.tpi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // DB
    implementation("org.xerial:sqlite-jdbc:3.49.1.0")

    // Test
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}