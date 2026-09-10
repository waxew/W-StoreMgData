package business_profile.product

/**
 * وضعیت فرم ایجاد و ویرایش محصول با فیلدهای اختصاصی پروفایل
 */
data class ProductEditDynamicState(
    val productId: String? = null,
    val dynamicValues: Map<String, String> = emptyMap()
)
