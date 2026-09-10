package business_profile.runtime

/**
 * وضعیت پروفایل فعال در زمان اجرای برنامه.
 * UI و Backend فقط از این لایه وضعیت را دریافت می کنند.
 */
data class ProfileRuntimeState(
    val profileId: String,
    val enabledModules: List<String>,
    val themeId: String,
    val dashboardId: String
)
