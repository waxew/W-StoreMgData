package business_profile.product

/**
 * UseCase پایه برای اتصال فیلدهای پویا به عملیات محصول.
 */
class ProductDynamicFieldUseCase {
    suspend fun attachDynamicFields(productId: String, fields: Map<String, String>) {
        // اتصال به Repository در لایه بعدی انجام می شود.
    }
}
