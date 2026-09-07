package com.wstore.engine.core.attribute

/**
 * مقدار واقعی یک Attribute برای یک Entity.
 *
 * مثال:
 * attributeId = imei
 * value = 123456789
 */
data class AttributeValue(
    val attributeId: String,
    val value: String
)
