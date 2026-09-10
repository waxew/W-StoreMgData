package business_profile.schema

/**
 * لایه قرارداد ذخیره سازی Attribute های پویا.
 * این Interface باعث می شود Profile ها به دیتابیس مستقیم وابسته نباشند.
 */
interface AttributeStorageRepository {
    fun save(attribute: AttributeValue)
    fun findByOwnerId(ownerId: String): List<AttributeValue>
    fun delete(ownerId: String)
}
