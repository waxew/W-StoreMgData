package business_profile.database

/**
 * لایه پایه تعریف دیتابیس Room برای Attribute های پویا.
 * اتصال واقعی به Room در مرحله اتصال ماژول دیتابیس تکمیل می‌شود.
 */
abstract class DynamicAttributeRoomDatabase {
    abstract fun dynamicAttributeDao(): DynamicAttributeDao
}
