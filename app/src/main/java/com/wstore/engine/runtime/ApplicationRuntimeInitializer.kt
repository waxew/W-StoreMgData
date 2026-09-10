package com.wstore.engine.runtime

import android.content.Context
import com.wstore.engine.config.AppConfigLoader
import com.wstore.engine.config.AppConfigRuntimeStore
import com.wstore.engine.update.UpdateChecker

/*
نام فایل:
ApplicationRuntimeInitializer.kt

ماژول:
Runtime

وظیفه:
راه‌اندازی ترتیب‌دار زیرساخت‌های مرکزی برنامه قبل از ساخت UI.
ابتدا App Config و سپس Business Profile فعال می‌شود و در صورت فعال بودن سیاست Update،
بررسی نسخه جدید به صورت غیرهمزمان شروع می‌شود.
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

        if (appConfig.update.checkOnStart) {
            UpdateChecker.checkAsync()
        }
    }
}
