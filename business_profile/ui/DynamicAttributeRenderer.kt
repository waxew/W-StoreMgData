package business_profile.ui

import business_profile.schema.AttributeDefinition

/**
 * نام فایل: DynamicAttributeRenderer.kt
 * ماژول: Business Profile UI
 * وظیفه:
 * تبدیل تعریف Attribute کسب و کار به قرارداد قابل مصرف توسط UI.
 * این لایه Core محصول را تغییر نمی‌دهد و بر اساس Schema کار می‌کند.
 */

interface DynamicAttributeRenderer {
    fun resolveComponent(attribute: AttributeDefinition): String
}

class DefaultDynamicAttributeRenderer : DynamicAttributeRenderer {
    override fun resolveComponent(attribute: AttributeDefinition): String {
        return when (attribute.type.lowercase()) {
            "text" -> "TEXT_FIELD"
            "number" -> "NUMBER_FIELD"
            "boolean" -> "SWITCH"
            "option" -> "DROPDOWN"
            "serial" -> "SERIAL_FIELD"
            else -> "TEXT_FIELD"
        }
    }
}
