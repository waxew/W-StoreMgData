package com.wstore.engine.presentation.attribute

import com.wstore.engine.core.attribute.AttributeDefinition

/**
 * Dynamic Attribute UI Field foundation.
 *
 * This layer converts runtime AttributeDefinition into UI components.
 * Business types must not be referenced here.
 */
object DynamicAttributeField {
    fun resolve(definition: AttributeDefinition): String {
        return definition.type.name
    }
}
