/*
================================================
نام فایل:
app/build.gradle.kts

وظیفه:
تنظیمات ماژول اصلی اندروید.

این ماژول خروجی APK نهایی را تولید می کند.

Core پروژه مستقل باقی می ماند و از این لایه استفاده می شود.
================================================
*/

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.wstore.engine"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.wstore.engine"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
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
