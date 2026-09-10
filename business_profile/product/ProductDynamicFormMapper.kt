package business_profile.product

import business_profile.schema.AttributeDefinition

/**
 * اتصال فرم محصول به فیلدهای پویا بر اساس Profile Schema.
 */
class ProductDynamicFormMapper {
    fun map(attributes: List<AttributeDefinition>): List<AttributeDefinition> {
        return attributes
    }
}
