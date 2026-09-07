package com.wstore.engine.presentation.attribute.components

/**
 * Dynamic numeric attribute field foundation.
 *
 * This component is driven by AttributeDefinition and is not aware of any
 * business type such as mobile, boutique, or beauty.
 */
class DynamicNumberField {
    fun render(value: String?): String {
        return value ?: ""
    }
}
