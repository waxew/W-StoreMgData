package business_profile.database

/**
 * مدل ذخیره سازی مقدار فیلدهای پویا.
 * این Entity مستقل از مدل های اصلی Product و Inventory است.
 */
data class DynamicAttributeEntity(
    val id: String,
    val ownerId: String,
    val attributeKey: String,
    val value: String
)
