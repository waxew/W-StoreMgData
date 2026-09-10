package business_profile.product

import business_profile.schema.AttributeDefinition

/**
 * اتصال فیلدهای پویا به فرم های Product.
 * Product Core بدون تغییر باقی می ماند.
 */
class ProductDynamicFieldMapper {
    fun map(attributes: List<AttributeDefinition>): List<String> {
        return attributes.map { it.key }
    }
}
