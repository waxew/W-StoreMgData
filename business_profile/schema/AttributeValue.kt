package com.wstore.business_profile.schema

/**
 * مدل مقداردهی فیلدهای پویا.
 *
 * این کلاس برای نگهداری مقدار Attribute های اختصاصی هر Business Profile استفاده می‌شود.
 * هدف جلوگیری از تغییر Core برای هر نوع کسب و کار است.
 */
data class AttributeValue(
    val attributeId: String,
    val value: String
)
