package com.wstore.engine.core.attribute

/**
 * موتور مرکزی مدیریت Attribute های پویا.
 *
 * این لایه تصمیم می گیرد Attribute ها چگونه در Runtime پردازش شوند.
 */
class DynamicAttributeEngine(
    private val validator: AttributeValidator = AttributeValidator()
) {

    fun validate(
        definition: AttributeDefinition,
        value: AttributeValue
    ): Boolean {
        return validator.validate(definition, value)
    }
}
