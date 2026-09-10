package business_profile.ui.compose

/**
 * رجیستری Componentهای Dynamic UI.
 * Schema بدون وابستگی مستقیم به UI Component نگهداری می‌شود.
 */

class DynamicComponentRegistry {
    fun resolve(type: String): String {
        return type
    }
}
