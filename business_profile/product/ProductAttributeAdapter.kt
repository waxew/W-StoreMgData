package business_profile.product

import business_profile.schema.AttributeValue

/**
 * آداپتور اتصال محصول به Attribute های پویا.
 *
 * هدف:
 * نگه داشتن Product Core بدون تغییر و افزودن فیلدهای اختصاصی هر کسب و کار.
 */
class ProductAttributeAdapter {
    fun attach(productId: String, attributes: List<AttributeValue>): ProductAttributeRecord {
        return ProductAttributeRecord(productId, attributes)
    }
}

 data class ProductAttributeRecord(
    val productId: String,
    val attributes: List<AttributeValue>
)
