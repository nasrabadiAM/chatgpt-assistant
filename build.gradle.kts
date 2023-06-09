import io.gitlab.arturbosch.detekt.Detekt

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detekt)
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
    include("**/*.kt", "**/*.kts")
    exclude("resources/", "build/")
}

allprojects {
    apply { from(file("$rootDir/gradle/config.gradle")) }
}

dependencies {
    detektPlugins(libs.detekt.formattingPlugin)
}