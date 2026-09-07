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
}

kotlin {
    jvmToolchain(17)
}
