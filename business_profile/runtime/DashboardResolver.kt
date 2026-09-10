package business_profile.runtime

/**
 * نام فایل: DashboardResolver.kt
 * ماژول: Business Profile Runtime
 * وظیفه:
 * تعیین داشبورد مناسب بر اساس پروفایل فعال کسب و کار.
 */

class DashboardResolver {
    fun resolve(profileId: String): String {
        return when (profileId) {
            "mobile_store_001" -> "mobile_dashboard"
            "boutique_001" -> "boutique_dashboard"
            else -> "default_dashboard"
        }
    }
}
