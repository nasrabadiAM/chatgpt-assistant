plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        /*
        Source sets structure
        common
         ├─ android
         ├─ desktop(or web)
         ├─ ios
             ├─ iosX64
             ├─ iosArm64
             ├─ iosSimulatorArm64
         */
        val commonMain by getting {
            dependencies {
                implementation(libs.openai)
                implementation(kotlin("stdlib-common"))
                // Network
                implementation(libs.ktor.core)
                implementation(libs.ktor.logging)
                // Coroutines
                implementation(libs.kotlinx.coroutines.core)
                // Logger
                implementation(libs.napier)
                // JSON
                implementation(libs.kotlinx.serialization.json)
                // Key-Value storage
                implementation(libs.multiplatform.settings)
                // DI
                api(libs.koin.core)
                // Datetime
                implementation(libs.kotlinx.datetime)
                implementation(libs.xml.serialization)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.test)
            }
        }

        val androidMain by getting {
            dependencies {
                // Network
                implementation(libs.ktor.client.okhttp)
            }
        }
        val androidUnitTest by getting

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                // Network
                implementation(libs.ktor.client.ios)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.nasrabadiam.shared"
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
    compileSdk = extra.get("compileSdk") as Int

    defaultConfig {
        minSdk = extra.get("minSdk") as Int
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}