package com.wstore.engine.config

import android.content.Context
import org.json.JSONObject

/*
نام فایل:
AppConfigLoader.kt

ماژول:
Application Config Engine

وظیفه:
خواندن فایل مرکزی app_config.json از Assets و تبدیل آن به مدل AppConfig.
تمام مقادیر پایه برنامه از این مسیر وارد Runtime می‌شوند.
*/

class AppConfigLoader(
    private val context: Context
) {

    fun load(): AppConfig {
        val jsonText = context.assets
            .open(APP_CONFIG_FILE)
            .bufferedReader()
            .use { reader -> reader.readText() }

        val root = JSONObject(jsonText)
        val app = root.getJSONObject("app")
        val company = root.getJSONObject("company")
        val startup = root.getJSONObject("startup")
        val ads = root.getJSONObject("ads")
        val update = root.getJSONObject("update")
        val backup = root.getJSONObject("backup")
        val ui = root.getJSONObject("ui")

        val config = AppConfig(
            schemaVersion = root.getInt("schemaVersion"),
            app = AppIdentityConfig(
                name = app.getString("name").trim(),
                applicationId = app.getString("applicationId").trim(),
                versionCode = app.getInt("versionCode"),
                versionName = app.getString("versionName").trim(),
                iconKey = app.getString("iconKey").trim(),
                logoKey = app.getString("logoKey").trim()
            ),
            company = CompanyConfig(
                name = company.getString("name").trim(),
                displayName = company.getString("displayName").trim(),
                logoKey = company.getString("logoKey").trim(),
                supportEmail = company.getString("supportEmail").trim()
            ),
            startup = StartupConfig(
                logoMotionEnabled = startup.getBoolean("logoMotionEnabled"),
                durationMillis = startup.getLong("durationMillis"),
                animation = startup.getString("animation").trim()
            ),
            ads = AdsConfig(
                enabled = ads.getBoolean("enabled"),
                showForGuest = ads.getBoolean("showForGuest"),
                showForExpiredSubscription = ads.getBoolean("showForExpiredSubscription"),
                hideForVip = ads.getBoolean("hideForVip"),
                provider = ads.getString("provider").trim(),
                bannerEnabled = ads.getBoolean("bannerEnabled"),
                interstitialEnabled = ads.getBoolean("interstitialEnabled")
            ),
            update = UpdateConfig(
                enabled = update.getBoolean("enabled"),
                checkOnStart = update.getBoolean("checkOnStart"),
                manifestUrl = update.getString("manifestUrl").trim(),
                showPopup = update.getBoolean("showPopup"),
                showNotificationBadge = update.getBoolean("showNotificationBadge")
            ),
            backup = BackupConfig(
                enabled = backup.getBoolean("enabled"),
                includeDatabase = backup.getBoolean("includeDatabase"),
                includePreferences = backup.getBoolean("includePreferences"),
                includeUserFiles = backup.getBoolean("includeUserFiles")
            ),
            ui = UiShellConfig(
                drawerEnabled = ui.getBoolean("drawerEnabled"),
                topBarEnabled = ui.getBoolean("topBarEnabled"),
                topBarCenterMode = ui.getString("topBarCenterMode").trim(),
                rootNavigationBackToHome = ui.getBoolean("rootNavigationBackToHome")
            )
        )

        validate(config)
        return config
    }

    private fun validate(config: AppConfig) {
        require(config.schemaVersion == SUPPORTED_SCHEMA_VERSION) {
            "نسخه App Config پشتیبانی نمی‌شود: ${config.schemaVersion}"
        }
        require(config.app.name.isNotBlank()) { "نام برنامه در App Config خالی است." }
        require(config.app.applicationId.isNotBlank()) { "applicationId در App Config خالی است." }
        require(config.app.versionCode > 0) { "versionCode باید بزرگ‌تر از صفر باشد." }
        require(config.app.versionName.isNotBlank()) { "versionName در App Config خالی است." }
        require(config.company.supportEmail.isNotBlank()) { "ایمیل پشتیبانی تعریف نشده است." }
        require(config.startup.durationMillis >= 0) { "مدت Logo Motion نمی‌تواند منفی باشد." }
    }

    companion object {
        const val APP_CONFIG_FILE = "app_config.json"
        const val SUPPORTED_SCHEMA_VERSION = 1
    }
}
