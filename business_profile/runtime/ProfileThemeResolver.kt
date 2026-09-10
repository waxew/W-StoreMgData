package business_profile.runtime

/**
 * Resolver پایه برای انتخاب Theme بر اساس Business Profile.
 * هر پروفایل می‌تواند هویت بصری مستقل داشته باشد.
 */
class ProfileThemeResolver {
    fun resolveTheme(profileId: String): String {
        return when (profileId) {
            "mobile_store_001" -> "mobile_theme"
            "boutique_001" -> "boutique_theme"
            else -> "default_theme"
        }
    }
}
