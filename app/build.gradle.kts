import groovy.json.JsonSlurper

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

/*
 * App Config مرکزی در مرحله Build نیز خوانده می‌شود تا نام پکیج و نسخه در کدهای مختلف
 * تکرار نشوند. پوشه config همچنین به Assets اضافه می‌شود تا همان فایل‌ها در Runtime خوانده شوند.
 */
val appConfigFile = rootProject.file("config/app_config.json")
require(appConfigFile.exists()) {
    "فایل config/app_config.json پیدا نشد."
}

@Suppress("UNCHECKED_CAST")
val appConfigRoot = JsonSlurper().parse(appConfigFile) as Map<String, Any?>
@Suppress("UNCHECKED_CAST")
val appIdentity = appConfigRoot["app"] as Map<String, Any?>

val configuredAppName = appIdentity.getValue("name").toString()
val configuredApplicationId = appIdentity.getValue("applicationId").toString()
val configuredVersionCode = (appIdentity.getValue("versionCode") as Number).toInt()
val configuredVersionName = appIdentity.getValue("versionName").toString()

android {
    namespace = "com.wstore.engine"
    compileSdk = 35

    defaultConfig {
        applicationId = configuredApplicationId
        minSdk = 26
        targetSdk = 35
        versionCode = configuredVersionCode
        versionName = configuredVersionName

        // نام نمایشی برنامه نیز از همان App Config تولید می‌شود.
        resValue("string", "app_name", configuredAppName)
    }

    sourceSets {
        getByName("main") {
            // یک منبع واحد برای App Config و تمام Business Profileها.
            assets.srcDir(rootProject.file("config"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.10.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation(platform("androidx.compose:compose-bom:2025.01.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-compiler:2.52")
}
