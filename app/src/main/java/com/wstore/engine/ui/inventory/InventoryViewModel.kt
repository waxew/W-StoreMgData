package com.wstore.engine.ui.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.local.entity.InventoryTransactionEntity
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * نام فایل: InventoryViewModel.kt
 * ماژول: Inventory
 * وظیفه: مدیریت کالاها، گردش موجودی و خطاهای ثبت ورود/خروج.
 */
@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductEntity>>(emptyList())
    val products: StateFlow<List<ProductEntity>> = _products

    val transactions: StateFlow<List<InventoryTransactionEntity>> =
        repository.observeTransactions().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            runCatching { repository.products() }
                .onSuccess { _products.value = it }
                .onFailure { throwable ->
                    _error.value = throwable.message ?: "خواندن فهرست کالاها انجام نشد."
                }
        }
    }

    fun stockIn(productId: Long, quantityText: String, note: String) {
        val quantity = quantityText.toIntOrNull()
        if (quantity == null || quantity <= 0) {
            _error.value = "تعداد ورود باید عددی بیشتر از صفر باشد."
            return
        }

        applyMovement {
            repository.stockIn(productId, quantity, note)
        }
    }

    fun stockOut(productId: Long, quantityText: String, note: String) {
        val quantity = quantityText.toIntOrNull()
        if (quantity == null || quantity <= 0) {
            _error.value = "تعداد خروج باید عددی بیشتر از صفر باشد."
            return
        }

        applyMovement {
            repository.stockOut(productId, quantity, note)
        }
    }

    private fun applyMovement(action: suspend () -> Unit) {
        viewModelScope.launch {
            runCatching { action() }
                .onSuccess {
                    _error.value = null
                    _products.value = repository.products()
                }
                .onFailure { throwable ->
                    _error.value = throwable.message ?: "ثبت گردش موجودی انجام نشد."
                }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
