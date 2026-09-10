package com.wstore.core.profile.schema

/**
 * Loader پایه برای Schema فیلدهای پویا
 *
 * این کلاس در مراحل بعدی به JSON schema متصل می‌شود.
 */
class DynamicAttributeSchemaLoader {

    fun normalize(attributes: List<DynamicAttributeDefinition>): List<DynamicAttributeDefinition> {
        return attributes
            .distinctBy { it.key }
    }
}
