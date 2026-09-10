package com.wstore.engine.data.model

/**
 * مدل استاندارد تعریف Attribute پویا.
 * این مدل برای جلوگیری از Hard Code شدن فیلدهای صنعتی استفاده می‌شود.
 */
data class AttributeDefinition(
    val key: String,
    val label: String,
    val type: String,
    val required: Boolean = false,
    val enabled: Boolean = true
)
