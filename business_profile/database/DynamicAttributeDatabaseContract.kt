package business_profile.database

/**
 * قرارداد پایه لایه دیتابیس برای Attribute های پویا.
 * این لایه باعث می شود پروفایل های کسب و کار بدون تغییر Core ذخیره شوند.
 */
interface DynamicAttributeDatabaseContract {
    suspend fun save(attribute: DynamicAttributeRecord)
    suspend fun getByOwner(ownerId: String): List<DynamicAttributeRecord>
    suspend fun delete(ownerId: String)
}

/**
 * مدل عمومی ذخیره Attribute.
 */
data class DynamicAttributeRecord(
    val ownerId: String,
    val fieldKey: String,
    val value: String
)
