package com.wstore.core.profile.ui

/**
 * تعیین کننده نوع Component برای فیلدهای Dynamic Attribute
 */
class DynamicFieldTypeResolver {

    fun resolve(type: String): String {
        return when (type.lowercase()) {
            "text" -> "TEXT_FIELD"
            "number" -> "NUMBER_FIELD"
            "decimal" -> "DECIMAL_FIELD"
            "boolean" -> "SWITCH_FIELD"
            "option" -> "DROPDOWN_FIELD"
            "serial" -> "SERIAL_FIELD"
            else -> "TEXT_FIELD"
        }
    }
}
