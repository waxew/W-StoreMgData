package business_profile.product

/**
 * اتصال وضعیت فرم پویا به عملیات ایجاد و ویرایش محصول.
 * Product Core مستقل باقی می ماند.
 */
class ProductDynamicFormStateBinder {

    fun bind(
        productId: String,
        attributes: Map<String, String>
    ): ProductDynamicPayload {
        return ProductDynamicPayload(
            productId = productId,
            attributes = attributes
        )
    }
}

data class ProductDynamicPayload(
    val productId: String,
    val attributes: Map<String, String>
)
