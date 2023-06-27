import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.github.ben-manes.versions").apply(false)
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("io.gitlab.arturbosch.detekt")
}

allprojects {
    // ./gradlew dependencyUpdates
    // Report: build/dependencyUpdates/report.txt
    apply(plugin = "com.github.ben-manes.versions")

    apply { from(file("$rootDir/gradle/config.gradle")) }

    val localPropertiesFile = rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        val properties = java.util.Properties().apply {
            load(localPropertiesFile.reader())
        }
        extra.apply {
            set("apiKey", properties["chatGptApiKey"])
        }
    }
}

tasks.register<Detekt>("detektAll") {
    description = "Runs detekt build..."
    setSource(files(projectDir))
    config.setFrom(files("$rootDir/detekt.yml"))
    parallel = true
    reports {
        xml.required.set(false)
        txt.required.set(false)
        html {
            required.set(false)
            outputLocation.set(file("build/reports/detekt.html"))
        }
    }
    include("**/*.kt")
    exclude("resources/", "**/build/**")
}

dependencies {
    detektPlugins(libs.detekt.formattingPlugin)
}