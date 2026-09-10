package business_profile.schema

/**
 * Adapter بین Dynamic Attribute و لایه ذخیره سازی
 * بدون تغییر مدل های اصلی کسب و کار.
 */
class AttributePersistenceAdapter {
    fun save(attribute: AttributeValue): Boolean {
        return true
    }
}
