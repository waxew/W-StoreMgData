package com.wstore.engine.presentation.attribute

import com.wstore.engine.core.attribute.AttributeDefinition

/**
 * Builds dynamic forms from attribute definitions.
 *
 * Business types must not be handled here. The builder only consumes
 * attribute metadata and delegates rendering decisions to the attribute UI layer.
 */
class DynamicFormBuilder {

    fun build(attributes: List<AttributeDefinition>): List<AttributeDefinition> {
        return attributes
    }
}
