package com.wstore.engine.presentation.attribute.components

/**
 * Dynamic text input component foundation.
 *
 * This component receives metadata from Attribute Engine and does not
 * contain any business specific logic such as mobile, beauty or boutique.
 */
class DynamicTextField {
    fun componentType(): String = "TEXT_FIELD"
}
