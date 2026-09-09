package com.wstore.engine.runtime

import android.content.Context
import com.wstore.engine.config.AppConfigLoader
import com.wstore.engine.config.AppConfigRuntimeStore

/*
نام فایل:
ApplicationRuntimeInitializer.kt

ماژول:
Runtime

وظیفه:
راه‌اندازی ترتیب‌دار زیرساخت‌های مرکزی برنامه قبل از ساخت UI.
ابتدا App Config و سپس Business Profile فعال می‌شود تا تمام لایه‌های بعدی فقط از Runtime Storeها بخوانند.
*/

object ApplicationRuntimeInitializer {

    fun initialize(context: Context) {
        val appContext = context.applicationContext
        val appConfig = AppConfigLoader(appContext).load()

        require(appContext.packageName == appConfig.app.applicationId) {
            "applicationId خروجی Build با App Config هماهنگ نیست."
        }

        AppConfigRuntimeStore.register(appConfig)
        BusinessRuntimeInitializer.initialize(appContext)
    }
}
