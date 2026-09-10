package business_profile.sales

import business_profile.schema.AttributeValue

/**
 * آداپتور ویژگی های اختصاصی فروش
 * برای اتصال Profile Attributes به فروش بدون تغییر Sales Core
 */
class SalesAttributeAdapter {
    fun attachAttributes(
        saleId: String,
        attributes: List<AttributeValue>
    ): SaleDynamicAttributes {
        return SaleDynamicAttributes(
            saleId = saleId,
            attributes = attributes
        )
    }
}

data class SaleDynamicAttributes(
    val saleId: String,
    val attributes: List<AttributeValue>
)
