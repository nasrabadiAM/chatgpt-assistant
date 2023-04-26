plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("library-module-plugin") {
            id = "library-module"
            implementationClass = "plugin.LibraryModulePlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))

    implementation(libs.android.gradleApi)
}