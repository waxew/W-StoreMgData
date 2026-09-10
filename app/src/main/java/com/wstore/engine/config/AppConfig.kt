package com.wstore.engine.config

/*
نام فایل:
AppConfig.kt

ماژول:
Application Config Engine

وظیفه:
مدل مرکزی تمام تنظیمات پایه برنامه که باید از یک فایل Config خوانده شوند.
کدهای UI و Runtime نباید نام برنامه، اطلاعات شرکت، سیاست تبلیغات و تنظیمات Update را Hard Code کنند.
*/

data class AppConfig(
    val schemaVersion: Int,
    val app: AppIdentityConfig,
    val company: CompanyConfig,
    val startup: StartupConfig,
    val ads: AdsConfig,
    val update: UpdateConfig,
    val backup: BackupConfig,
    val ui: UiShellConfig
)

data class AppIdentityConfig(
    val name: String,
    val applicationId: String,
    val versionCode: Int,
    val versionName: String,
    val iconKey: String,
    val logoKey: String
)

data class CompanyConfig(
    val name: String,
    val displayName: String,
    val logoKey: String,
    val supportEmail: String
)

data class StartupConfig(
    val logoMotionEnabled: Boolean,
    val durationMillis: Long,
    val animation: String
)

data class AdsConfig(
    val enabled: Boolean,
    val showForGuest: Boolean,
    val showForExpiredSubscription: Boolean,
    val hideForVip: Boolean,
    val provider: String,
    val bannerEnabled: Boolean,
    val interstitialEnabled: Boolean
)

data class UpdateConfig(
    val enabled: Boolean,
    val checkOnStart: Boolean,
    val manifestUrl: String,
    val showPopup: Boolean,
    val showNotificationBadge: Boolean
)

data class BackupConfig(
    val enabled: Boolean,
    val includeDatabase: Boolean,
    val includePreferences: Boolean,
    val includeUserFiles: Boolean
)

data class UiShellConfig(
    val drawerEnabled: Boolean,
    val topBarEnabled: Boolean,
    val topBarCenterMode: String,
    val rootNavigationBackToHome: Boolean
)
