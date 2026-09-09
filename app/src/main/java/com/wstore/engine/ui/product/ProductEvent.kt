package com.wstore.engine.ui.product

import com.wstore.engine.data.model.Product

/**
 * نام فایل: ProductEvent.kt
 * ماژول: Product UI
 * وظیفه: قرارداد رویدادهای صفحه کالا شامل فیلدهای پایه و Attributeهای Profile فعال.
 *
 * موجودی فقط هنگام ایجاد کالا مقدار اولیه می‌گیرد؛ تغییرات بعدی موجودی از Inventory انجام می‌شوند.
 */
sealed interface ProductEvent {
    data class AddProduct(
        val name: String,
        val code: String,
        val category: String,
        val price: Double,
        val stock: Int,
        val attributes: Map<String, String> = emptyMap()
    ) : ProductEvent

    data class UpdateProduct(
        val id: Long,
        val name: String,
        val code: String,
        val category: String,
        val price: Double,
        val attributes: Map<String, String> = emptyMap()
    ) : ProductEvent

    data class DeleteProduct(val product: Product) : ProductEvent
    data class Search(val query: String) : ProductEvent
    data class StartEdit(val product: Product) : ProductEvent
    data object CancelEdit : ProductEvent
    data object DismissMessage : ProductEvent
}
