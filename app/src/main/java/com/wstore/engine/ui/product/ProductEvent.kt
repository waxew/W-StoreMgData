package com.wstore.engine.ui.product

sealed interface ProductEvent {
    data class AddProduct(
        val name: String,
        val code: String,
        val category: String,
        val price: Double,
        val stock: Int
    ) : ProductEvent
}
