package com.wstore.engine.ui.product

import com.wstore.engine.data.model.Product

/**
 * رویدادهای UI ماژول کالا.
 */
sealed interface ProductEvent {
    data class AddProduct(
        val name: String,
        val code: String,
        val category: String,
        val price: Double,
        val stock: Int
    ) : ProductEvent

    data class UpdateProduct(
        val id: Long,
        val name: String,
        val code: String,
        val category: String,
        val price: Double,
        val stock: Int
    ) : ProductEvent

    data class DeleteProduct(val product: Product) : ProductEvent
    data class Search(val query: String) : ProductEvent
    data class StartEdit(val product: Product) : ProductEvent
    data object CancelEdit : ProductEvent
    data object DismissMessage : ProductEvent
}
