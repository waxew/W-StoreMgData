package com.wstore.engine.ui.product

import com.wstore.engine.data.model.Product

sealed interface ProductState {
    data object Loading : ProductState
    data class Success(val products: List<Product>) : ProductState
    data class Error(val message: String) : ProductState
}
