package business_profile.database

/**
 * مدل Room برای ذخیره مقدار فیلدهای پویا.
 * این Entity مستقل از مدل های اصلی کسب و کار است.
 */
data class DynamicAttributeRoomEntity(
    val id: Long,
    val ownerId: String,
    val attributeKey: String,
    val attributeValue: String,
    val profileId: String
)
