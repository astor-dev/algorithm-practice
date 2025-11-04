plugins {
    application
    java
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(libs.guava)
    implementation(kotlin("stdlib-jdk8"))
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "Main"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
