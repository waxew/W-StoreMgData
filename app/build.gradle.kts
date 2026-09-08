/*
================================================
نام فایل:
app/build.gradle.kts

Android application module configuration.
================================================
*/

import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

val versionProperties = Properties()
val versionFile = rootProject.file("version.properties")
if (versionFile.exists()) {
    versionProperties.load(versionFile.inputStream())
}

android {
    namespace = "com.wstore.engine"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.wstore.engine"
        minSdk = 26
        targetSdk = 35
        versionCode = versionProperties.getProperty("VERSION_CODE", "10000").toInt()
        versionName = versionProperties.getProperty("VERSION_NAME", "1.0.0")
    }

    signingConfigs {
        create("release") {
            storeFile = file(System.getenv("KEYSTORE_FILE") ?: "release.keystore")
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: ""
            keyAlias = System.getenv("KEY_ALIAS") ?: ""
            keyPassword = System.getenv("KEY_PASSWORD") ?: ""
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-compiler:2.52")
}

kotlin {
    jvmToolchain(17)
}
