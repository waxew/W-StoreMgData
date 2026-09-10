package business_profile.ui

/**
 * نگهداری وضعیت فیلدهای پویا برای فرم‌های Runtime.
 */
data class DynamicFieldStateHolder(
    val values: Map<String, String> = emptyMap()
)
