package business_profile.product

/**
 * اتصال غیرمستقیم Product به Attribute های اختصاصی Profile.
 * مدل اصلی Product تغییر نمی‌کند و فیلدهای اختصاصی جدا مدیریت می‌شوند.
 */
data class ProductDynamicFieldBinding(
    val productId: String,
    val attributes: Map<String, String>
)
