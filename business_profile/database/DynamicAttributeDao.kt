package business_profile.database

/**
 * قرارداد دسترسی به ذخیره سازی Attribute های پویا.
 * این لایه مستقل از Room نگه داشته می شود تا معماری قابل توسعه باشد.
 */
interface DynamicAttributeDao {
    suspend fun save(entity: DynamicAttributeEntity)

    suspend fun update(entity: DynamicAttributeEntity)

    suspend fun delete(attributeId: String)

    suspend fun findByOwner(ownerId: String): List<DynamicAttributeEntity>
}
