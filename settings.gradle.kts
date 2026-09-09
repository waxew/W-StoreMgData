/*
================================================
نام فایل:
settings.gradle.kts

وظیفه:
تعریف ساختار ماژول های پروژه اندروید.

این فایل نقطه شروع Gradle برای Android Studio است.
================================================
*/

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "W-StoreMgData"
include(":app")
include(":core")
