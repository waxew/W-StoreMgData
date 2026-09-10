package business_profile.di

/**
 * ماژول تزریق وابستگی برای بخش Dynamic Attribute.
 *
 * این فایل به عنوان نقطه اتصال Repository، DAO و Database نگهداری می‌شود
 * تا در مرحله اتصال Hilt واقعی فقط Binding ها تکمیل شوند.
 */
object DynamicAttributeModule {
    fun description(): String {
        return "Dynamic Attribute dependency module foundation"
    }
}
