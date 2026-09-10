package business_profile.database

/**
 * پیاده سازی اولیه Repository برای تست Runtime.
 * در مرحله Room Integration با DAO واقعی جایگزین می شود.
 */
class InMemoryDynamicAttributeRepository(
    private val dao: DynamicAttributeDao
) {
    suspend fun save(attribute: DynamicAttributeEntity) {
        dao.save(attribute)
    }

    suspend fun getByOwner(ownerId: String): List<DynamicAttributeEntity> {
        return dao.findByOwner(ownerId)
    }
}
