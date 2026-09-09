/*
================================================
نام فایل:
AttributeValue.kt

بخش:
Core Attribute Engine

وظیفه:
نگهداری مقدار واقعی Attribute برای هر رکورد.

مثال:
Attribute:
Color

Value:
Black

Attribute:
Storage

Value:
256GB
================================================
*/

package com.wstore.engine.core.attribute

/**
 * مقدار یک ویژگی پویا
 */
data class AttributeValue(
    val attributeId: String,
    val value: String
)
