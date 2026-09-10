package business_profile.runtime

/**
 * قرارداد اتصال Profile فعال به صفحات برنامه.
 *
 * UI بر اساس Profile تصمیم می گیرد چه Screen هایی نمایش داده شوند.
 */
class ProfileScreenBinding {
    fun resolve(profileId: String): String {
        return "${profileId}_dashboard"
    }
}
