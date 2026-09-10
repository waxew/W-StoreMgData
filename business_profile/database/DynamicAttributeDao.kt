package business_profile.database

/**
 * قرارداد دسترسی به ذخیره سازی Attribute های پویا.
 */
interface DynamicAttributeDao {
    fun save(entity: DynamicAttributeEntity)
    fun findByOwner(ownerId: String): List<DynamicAttributeEntity>
}
