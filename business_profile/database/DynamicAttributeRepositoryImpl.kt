package business_profile.database

/**
 * پیاده سازی اولیه Repository برای Attribute های پویا.
 */
class DynamicAttributeRepositoryImpl(
    private val dao: DynamicAttributeDao
) {
    fun save(entity: DynamicAttributeEntity) {
        dao.save(entity)
    }

    fun find(ownerId: String): List<DynamicAttributeEntity> {
        return dao.findByOwner(ownerId)
    }
}
