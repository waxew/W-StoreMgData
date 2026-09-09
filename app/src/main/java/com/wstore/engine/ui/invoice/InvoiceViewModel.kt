package com.wstore.engine.ui.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.local.entity.InvoiceEntity
import com.wstore.engine.data.local.entity.InvoiceItemEntity
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.data.repository.InvoiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * نام فایل: InvoiceViewModel.kt
 * ماژول: Invoice
 * وظیفه: مدیریت فهرست فاکتورها، فروش‌های بدون فاکتور و جزئیات فاکتور انتخاب‌شده.
 */
@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val repository: InvoiceRepository
) : ViewModel() {

    val invoices: StateFlow<List<InvoiceEntity>> = repository.observeInvoices()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val salesWithoutInvoice: StateFlow<List<SaleEntity>> =
        repository.observeSalesWithoutInvoice()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    private val _selectedInvoiceId = MutableStateFlow<Long?>(null)
    val selectedInvoiceId: StateFlow<Long?> = _selectedInvoiceId

    val selectedItems: StateFlow<List<InvoiceItemEntity>> = _selectedInvoiceId
        .flatMapLatest { invoiceId ->
            if (invoiceId == null) {
                flowOf(emptyList())
            } else {
                repository.observeInvoiceItems(invoiceId)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun createInvoice(saleId: Long) {
        viewModelScope.launch {
            runCatching { repository.createFromSale(saleId) }
                .onSuccess { invoiceId ->
                    _error.value = null
                    _selectedInvoiceId.value = invoiceId
                }
                .onFailure { throwable ->
                    _error.value = throwable.message ?: "ساخت فاکتور انجام نشد."
                }
        }
    }

    fun selectInvoice(invoiceId: Long) {
        _selectedInvoiceId.value = invoiceId
    }

    fun clearSelection() {
        _selectedInvoiceId.value = null
    }

    fun clearError() {
        _error.value = null
    }
}
