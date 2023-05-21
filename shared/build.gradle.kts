plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
}

kotlin {
    android()

    ios()
    iosSimulatorArm64()


    cocoapods {
        summary = "Shared code for the chat gpt app"
        ios.deploymentTarget = "14.1"
        homepage = "https://github.com/nasrabadiAM/chatgpt-assistant"
        version = "1.12.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
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
                // Compose
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

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

                api(libs.activity.compose)
                api(libs.androidx.appcompat)
                api(libs.androidx.core)
            }
        }
        val androidUnitTest by getting

        val iosMain by getting
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}