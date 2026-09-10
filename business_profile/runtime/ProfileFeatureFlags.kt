package business_profile.runtime

/**
 * Feature flags runtime برای فعال/غیرفعال کردن قابلیت های هر Business Profile.
 */
data class ProfileFeatureFlags(
    val productEnabled: Boolean = true,
    val inventoryEnabled: Boolean = false,
    val salesEnabled: Boolean = false,
    val invoiceEnabled: Boolean = false,
    val repairEnabled: Boolean = false
)
