package com.wstore.engine.core.attribute

/**
 * تعریف یک فیلد پویا برای موجودیت‌های سیستم.
 * مثال:
 * IMEI برای موبایل
 * Size برای پوشاک
 * Expiration برای محصولات دارای تاریخ انقضا
 */
data class AttributeDefinition(
    val id: String,
    val title: String,
    val type: AttributeType,
    val required: Boolean = false
)
