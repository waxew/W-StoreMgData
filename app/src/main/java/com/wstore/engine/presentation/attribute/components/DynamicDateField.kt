package com.wstore.engine.presentation.attribute.components

/**
 * Dynamic date attribute field foundation.
 * Used for date based attributes like warranty or expiration.
 */
class DynamicDateField {
    fun render(value: String?): String {
        return value ?: ""
    }
}
