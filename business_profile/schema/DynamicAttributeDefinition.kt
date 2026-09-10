package com.wstore.core.profile.schema

/**
 * مدل تعریف فیلدهای پویا برای Business Profile
 *
 * این مدل باعث می‌شود فیلدهای اختصاصی هر کسب و کار بدون تغییر Core اضافه شوند.
 */
data class DynamicAttributeDefinition(
    val key: String,
    val title: String,
    val type: String,
    val required: Boolean = false,
    val options: List<String> = emptyList()
)
