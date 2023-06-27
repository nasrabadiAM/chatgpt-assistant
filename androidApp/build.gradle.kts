plugins {
    kotlin("multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
}

kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
            }
        }
    }
}

android {
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    val appId = extra.get("applicationId") as String
    namespace = appId
    compileSdk = extra.get("compileSdk") as Int

    namespace = appId

    defaultConfig {
        minSdk = extra.get("minSdk") as Int
        targetSdk = extra.get("targetSdk") as Int

        applicationId = appId
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}