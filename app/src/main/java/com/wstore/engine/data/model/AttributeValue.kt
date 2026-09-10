package com.wstore.engine.data.model

/**
 * مقدار ثبت شده برای Attributeهای پویا.
 * نمونه‌ها: IMEI، شماره سریال، گارانتی و ویژگی‌های اختصاصی پروفایل.
 */
data class AttributeValue(
    val id: Long = 0,
    val ownerId: String,
    val attributeKey: String,
    val value: String
)
