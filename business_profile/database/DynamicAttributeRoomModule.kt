package business_profile.database

/**
 * ماژول پایه Database برای مدیریت Attribute های پویا.
 * در مرحله بعد به Room و Dependency Injection متصل می‌شود.
 */
object DynamicAttributeRoomModule {
    fun provideDatabaseName(): String = "dynamic_attribute_database"
}
