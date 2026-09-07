package com.wstore.engine.core.attribute

/**
 * Resolver لایه Attribute Engine.
 *
 * وظیفه این کلاس تبدیل AttributeDefinition به نوع فیلد قابل نمایش در UI است.
 * Core فقط نوع داده را می شناسد و هیچ وابستگی به Business Profile ندارد.
 */
class DynamicFieldTypeResolver {

    fun resolve(definition: AttributeDefinition): AttributeType {
        return definition.type
    }
}
