package com.wstore.engine.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.model.Product
import com.wstore.engine.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    fun loadProducts() {
        viewModelScope.launch {
            _products.value = repository.products().map {
                Product(
                    id = it.id,
                    name = it.name,
                    code = it.code,
                    category = it.category,
                    price = it.price,
                    stock = it.stock
                )
            }
        }
    }

    fun addProduct(event: ProductEvent.AddProduct) {
        viewModelScope.launch {
            repository.add(
                ProductEntity(
                    name = event.name,
                    code = event.code,
                    category = event.category,
                    price = event.price,
                    stock = event.stock
                )
            )
            loadProducts()
        }
    }
}
