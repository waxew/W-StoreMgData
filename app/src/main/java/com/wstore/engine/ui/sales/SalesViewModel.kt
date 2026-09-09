package com.wstore.engine.ui.sales

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.local.entity.CustomerEntity
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.data.model.SaleLineDraft
import com.wstore.engine.data.repository.CustomerRepository
import com.wstore.engine.data.repository.ProductRepository
import com.wstore.engine.data.repository.SalesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel ماژول فروش.
 * سبد فروش را مدیریت می‌کند و ثبت نهایی را به SalesRepository می‌سپارد.
 */
@HiltViewModel
class SalesViewModel @Inject constructor(
    private val salesRepository: SalesRepository,
    customerRepository: CustomerRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    val customers: StateFlow<List<CustomerEntity>> =
        customerRepository.observeCustomers().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val sales: StateFlow<List<SaleEntity>> =
        salesRepository.observeSales().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _products = MutableStateFlow<List<ProductEntity>>(emptyList())
    val products: StateFlow<List<ProductEntity>> = _products

    private val _cart = MutableStateFlow<List<SaleLineDraft>>(emptyList())
    val cart: StateFlow<List<SaleLineDraft>> = _cart

    private val _selectedCustomerId = MutableStateFlow<Long?>(null)
    val selectedCustomerId: StateFlow<Long?> = _selectedCustomerId

    private val _note = MutableStateFlow("")
    val note: StateFlow<String> = _note

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _success = MutableStateFlow<String?>(null)
    val success: StateFlow<String?> = _success

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            runCatching { productRepository.products() }
                .onSuccess { _products.value = it }
                .onFailure { throwable ->
                    _error.value = throwable.message ?: "خواندن فهرست کالاها انجام نشد."
                }
        }
    }

    fun selectCustomer(customerId: Long?) {
        _selectedCustomerId.value = customerId
    }

    fun setNote(value: String) {
        _note.value = value
    }

    fun addToCart(productId: Long, quantityText: String) {
        val quantity = quantityText.toIntOrNull()
        if (quantity == null || quantity <= 0) {
            _error.value = "تعداد فروش باید عددی بیشتر از صفر باشد."
            return
        }

        val product = _products.value.firstOrNull { it.id == productId }
        if (product == null) {
            _error.value = "کالای انتخاب‌شده پیدا نشد."
            return
        }

        val currentQuantity = _cart.value.firstOrNull { it.productId == productId }?.quantity ?: 0
        val newQuantity = currentQuantity + quantity
        if (newQuantity > product.stock) {
            _error.value = "موجودی ${product.name} برای این تعداد کافی نیست."
            return
        }

        _cart.value = _cart.value
            .filterNot { it.productId == productId } + SaleLineDraft(productId, newQuantity)
        _error.value = null
    }

    fun removeFromCart(productId: Long) {
        _cart.value = _cart.value.filterNot { it.productId == productId }
    }

    fun submitSale() {
        val lines = _cart.value
        if (lines.isEmpty()) {
            _error.value = "سبد فروش خالی است."
            return
        }

        viewModelScope.launch {
            runCatching {
                salesRepository.createSale(
                    customerId = _selectedCustomerId.value,
                    lines = lines,
                    note = _note.value
                )
            }.onSuccess { saleId ->
                _cart.value = emptyList()
                _note.value = ""
                _error.value = null
                _success.value = "فروش شماره $saleId با موفقیت ثبت شد."
                _products.value = productRepository.products()
            }.onFailure { throwable ->
                _error.value = throwable.message ?: "ثبت فروش انجام نشد."
            }
        }
    }

    fun clearMessages() {
        _error.value = null
        _success.value = null
    }
}
