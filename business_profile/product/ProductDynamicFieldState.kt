package business_profile.product

/**
 * وضعیت فیلدهای پویا هنگام ایجاد یا ویرایش محصول.
 */
data class ProductDynamicFieldState(
    val productId: String,
    val attributes: Map<String, String>
)
