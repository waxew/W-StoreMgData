package core.config

/**
 * مدل مرکزی تنظیمات برنامه.
 *
 * وظیفه:
 * نگهداری تنظیمات عمومی برنامه که از app_config.json خوانده می شود.
 *
 * این مدل نباید شامل منطق کسب و کار باشد و فقط تنظیمات Runtime را نگهداری می کند.
 */
data class AppConfig(
    val appName: String,
    val version: String,
    val activeProfile: String,
    val adsEnabled: Boolean,
    val disableAdsForVip: Boolean,
    val updateCheckEnabled: Boolean,
    val drawerEnabled: Boolean,
    val logoMotionEnabled: Boolean
)
