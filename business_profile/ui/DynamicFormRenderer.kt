package business_profile.ui

import business_profile.schema.AttributeDefinition

/**
 * نام فایل: DynamicFormRenderer.kt
 * ماژول: Business Profile UI
 * وظیفه:
 * ساخت فرم پویا بر اساس Schema پروفایل.
 * UI نباید برای هر کسب و کار Hard Code شود.
 */

class DynamicFormRenderer(
    private val attributeRenderer: DynamicAttributeRenderer
) {
    fun render(attributes: List<AttributeDefinition>): List<String> {
        return attributes.map {
            attributeRenderer.resolveComponent(it)
        }
    }
}
