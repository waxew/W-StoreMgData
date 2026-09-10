package com.wstore.engine.config

/*
نام فایل:
AppConfigRuntimeStore.kt

ماژول:
Application Config Engine

وظیفه:
ارائه تنظیمات مرکزی برنامه به لایه‌های مختلف بدون تکرار مقدارهای ثابت در کد.
این Store فقط خواندنی است و پس از شروع برنامه مقداردهی می‌شود.
*/

object AppConfigRuntimeStore {
    private var config: AppConfig? = null

    fun register(appConfig: AppConfig) {
        config = appConfig
    }

    fun current(): AppConfig =
        checkNotNull(config) {
            "App Config هنوز مقداردهی اولیه نشده است."
        }

    fun currentOrNull(): AppConfig? = config
}
