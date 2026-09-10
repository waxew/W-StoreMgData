package business_profile.database

/**
 * Repository abstraction برای ارتباط بین Dynamic Attribute و Database.
 */
interface DynamicAttributeDatabaseRepository {
    suspend fun save(entity: DynamicAttributeRoomEntity)
    suspend fun findByOwner(ownerId: String): List<DynamicAttributeRoomEntity>
}
