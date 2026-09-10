package business_profile.database

/**
 * DAO لایه Room برای Attribute های پویا.
 * این قرارداد برای اتصال واقعی به Room Database استفاده می‌شود.
 */
interface DynamicAttributeRoomDao {
    fun insert(entity: DynamicAttributeRoomEntity)
    fun update(entity: DynamicAttributeRoomEntity)
    fun delete(entity: DynamicAttributeRoomEntity)
    fun findByOwnerId(ownerId: String): List<DynamicAttributeRoomEntity>
}
