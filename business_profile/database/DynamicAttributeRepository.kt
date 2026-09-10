package business_profile.database

/**
 * قرارداد Repository برای مدیریت مقادیر فیلدهای پویا.
 * این لایه باعث می‌شود Profile ها بدون وابستگی مستقیم به دیتابیس کار کنند.
 */
interface DynamicAttributeRepository {
    suspend fun save(attribute: DynamicAttributeEntity)
    suspend fun getByOwner(ownerId: String): List<DynamicAttributeEntity>
    suspend fun delete(ownerId: String)
}
