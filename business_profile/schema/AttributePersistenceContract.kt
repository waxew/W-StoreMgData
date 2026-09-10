package business_profile.schema

/**
 * قرارداد ذخیره سازی مقادیر Attribute های پویا
 * لایه Database در مرحله بعد به این قرارداد متصل می شود.
 */
interface AttributePersistenceContract {
    fun save(value: AttributeValue)
    fun get(entityId: String): List<AttributeValue>
    fun delete(entityId: String)
}
