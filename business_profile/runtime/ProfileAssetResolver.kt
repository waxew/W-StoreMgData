package business_profile.runtime

/**
 * مدیریت مسیر Asset های اختصاصی پروفایل.
 * برای جداسازی منابع گرافیکی هر کسب و کار از Core استفاده می‌شود.
 */
class ProfileAssetResolver {
    fun resolveAssetGroup(profileId: String): String {
        return "assets/$profileId"
    }
}
