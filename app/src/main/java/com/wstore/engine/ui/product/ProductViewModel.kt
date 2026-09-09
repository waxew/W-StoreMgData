package com.wstore.engine.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.model.Product
import com.wstore.engine.data.model.ProductDeleteResult
import com.wstore.engine.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel ماژول کالا.
 *
 * لیست کالاها از Room به‌صورت Flow دریافت می‌شود. جستجو روی لیست زنده اعمال می‌شود و
 * حذف کالا فقط از مسیر محافظت‌شده Repository انجام می‌گیرد.
 */
@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products.asStateFlow()

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _editingProduct = MutableStateFlow<Product?>(null)
    val editingProduct: StateFlow<Product?> = _editingProduct.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    init {
        observeProducts()
    }

    /**
     * برای سازگاری با فراخوانی‌های قبلی حفظ شده است.
     * Flow اصلی نیز تغییرات دیتابیس را به صورت خودکار دنبال می‌کند.
     */
    fun loadProducts() {
        viewModelScope.launch {
            _allProducts.value = repository.products().map { it.toModel() }
            applyFilter()
        }
    }

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.AddProduct -> addProduct(event)
            is ProductEvent.UpdateProduct -> updateProduct(event)
            is ProductEvent.DeleteProduct -> deleteProduct(event.product)
            is ProductEvent.Search -> search(event.query)
            is ProductEvent.StartEdit -> _editingProduct.value = event.product
            ProductEvent.CancelEdit -> _editingProduct.value = null
            ProductEvent.DismissMessage -> _message.value = null
        }
    }

    fun addProduct(event: ProductEvent.AddProduct) {
        val validationError = validateProductInput(
            name = event.name,
            price = event.price,
            stock = event.stock
        )
        if (validationError != null) {
            _message.value = validationError
            return
        }

        viewModelScope.launch {
            runCatching {
                repository.add(
                    ProductEntity(
                        name = event.name.trim(),
                        code = event.code.trim(),
                        category = event.category.trim(),
                        price = event.price,
                        stock = event.stock
                    )
                )
            }.onSuccess {
                _message.value = "کالا با موفقیت ثبت شد."
            }.onFailure { error ->
                _message.value = error.message ?: "ثبت کالا انجام نشد."
            }
        }
    }

    private fun updateProduct(event: ProductEvent.UpdateProduct) {
        val validationError = validateProductInput(
            name = event.name,
            price = event.price,
            stock = 0
        )
        if (validationError != null) {
            _message.value = validationError
            return
        }

        viewModelScope.launch {
            runCatching {
                val current = repository.getById(event.id)
                    ?: error("کالای انتخاب‌شده در دیتابیس پیدا نشد.")

                repository.update(
                    current.copy(
                        name = event.name.trim(),
                        code = event.code.trim(),
                        category = event.category.trim(),
                        price = event.price
                    )
                )
            }.onSuccess {
                _editingProduct.value = null
                _message.value = "اطلاعات کالا ویرایش شد."
            }.onFailure { error ->
                _message.value = error.message ?: "ویرایش کالا انجام نشد."
            }
        }
    }

    private fun deleteProduct(product: Product) {
        viewModelScope.launch {
            runCatching {
                repository.delete(product.toEntity())
            }.onSuccess { result ->
                _message.value = when (result) {
                    ProductDeleteResult.Deleted -> "کالا حذف شد."
                    ProductDeleteResult.NotFound -> "کالا قبلاً حذف شده یا پیدا نشد."
                    is ProductDeleteResult.BlockedByHistory -> {
                        "این کالا سابقه فروش/فاکتور دارد و برای حفظ تاریخچه قابل حذف نیست."
                    }
                }
            }.onFailure { error ->
                _message.value = error.message ?: "حذف کالا انجام نشد."
            }
        }
    }

    private fun search(query: String) {
        _query.value = query
        applyFilter()
    }

    private fun observeProducts() {
        viewModelScope.launch {
            repository.observeProducts().collect { entities ->
                _allProducts.value = entities.map { it.toModel() }
                applyFilter()
            }
        }
    }

    private fun applyFilter() {
        val normalizedQuery = _query.value.trim()
        val source = _allProducts.value

        _products.value = if (normalizedQuery.isBlank()) {
            source
        } else {
            source.filter { product ->
                product.name.contains(normalizedQuery, ignoreCase = true) ||
                    product.code.contains(normalizedQuery, ignoreCase = true) ||
                    product.category.contains(normalizedQuery, ignoreCase = true)
            }
        }
    }

    private fun validateProductInput(
        name: String,
        price: Double,
        stock: Int
    ): String? {
        return when {
            name.isBlank() -> "نام کالا الزامی است."
            price < 0.0 -> "قیمت کالا نمی‌تواند منفی باشد."
            stock < 0 -> "موجودی اولیه نمی‌تواند منفی باشد."
            else -> null
        }
    }

    private fun ProductEntity.toModel(): Product = Product(
        id = id,
        name = name,
        code = code,
        category = category,
        price = price,
        stock = stock
    )

    private fun Product.toEntity(): ProductEntity = ProductEntity(
        id = id,
        name = name,
        code = code,
        category = category,
        price = price,
        stock = stock
    )
}
