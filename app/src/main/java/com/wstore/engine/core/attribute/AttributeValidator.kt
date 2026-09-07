package com.wstore.engine.core.attribute

/**
 * Validates dynamic attribute values based on their definitions.
 *
 * Validation rules remain generic and are not connected to any business.
 */
class AttributeValidator {

    fun validate(
        definition: AttributeDefinition,
        value: AttributeValue
    ): Boolean {
        if (definition.required && value.value.isBlank()) {
            return false
        }

        return true
    }
}
