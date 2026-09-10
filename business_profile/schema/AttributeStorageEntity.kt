package business_profile.schema

/**
 * مدل ذخیره‌سازی مقدار فیلدهای پویا.
 * این مدل برای نگهداری Attribute های پروفایل بدون تغییر Entity های اصلی استفاده می‌شود.
 */
data class AttributeStorageEntity(
    val ownerId: String,
    val attributeKey: String,
    val value: String,
    val valueType: String
)
