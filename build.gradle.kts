plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.commons.logging)
    implementation(libs.spring.beans)
    implementation(libs.spring.context)
    implementation(libs.spring.context.support)
    implementation(libs.spring.core)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.all)
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

application {
    // Define the main class for the application.
    mainClass = "io.github.travisdeshotels.lcr.driver.Driver"
}
