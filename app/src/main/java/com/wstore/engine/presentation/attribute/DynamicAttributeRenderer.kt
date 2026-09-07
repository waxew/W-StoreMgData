package com.wstore.engine.presentation.attribute

import com.wstore.engine.core.attribute.AttributeDefinition
import com.wstore.engine.core.attribute.AttributeType

/**
 * Dynamic Attribute Renderer
 *
 * مسئول تبدیل Attribute Definition به مدل قابل نمایش در UI است.
 *
 * این کلاس نباید هیچ شناختی از نوع کسب و کار داشته باشد.
 * فیلدهایی مثل IMEI، Size یا Expiration در Business Profile تعریف می‌شوند
 * و فقط بر اساس نوع Attribute رندر می‌شوند.
 */
class DynamicAttributeRenderer {

    fun resolveComponentType(
        definition: AttributeDefinition
    ): String {
        return when (definition.type) {
            AttributeType.TEXT -> "TEXT_FIELD"
            AttributeType.NUMBER -> "NUMBER_FIELD"
            AttributeType.DATE -> "DATE_PICKER"
            AttributeType.BOOLEAN -> "BOOLEAN_SWITCH"
            AttributeType.ENUM -> "DROPDOWN"
        }
    }
}
