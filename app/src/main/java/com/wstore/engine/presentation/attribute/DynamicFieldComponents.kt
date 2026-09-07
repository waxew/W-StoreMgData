package com.wstore.engine.presentation.attribute

/**
 * کامپوننت های پایه Dynamic Attribute UI.
 *
 * این لایه نباید هیچ شناختی از نوع کسب و کار داشته باشد.
 * تصمیم گیری فقط بر اساس AttributeType و Schema انجام می شود.
 */
object DynamicFieldComponents {
    const val TEXT_FIELD = "TEXT_FIELD"
    const val NUMBER_FIELD = "NUMBER_FIELD"
    const val DATE_PICKER = "DATE_PICKER"
    const val DROPDOWN = "DROPDOWN"
    const val BOOLEAN_SWITCH = "BOOLEAN_SWITCH"
}
