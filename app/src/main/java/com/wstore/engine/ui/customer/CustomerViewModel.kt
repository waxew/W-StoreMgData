package com.wstore.engine.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.local.entity.CustomerEntity
import com.wstore.engine.data.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * نام فایل: CustomerViewModel.kt
 * ماژول: Customer
 * وظیفه: مدیریت وضعیت UI مشتری، جستجو و عملیات CRUD.
 */
@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val repository: CustomerRepository
) : ViewModel() {

    private val query = MutableStateFlow("")

    val customers: StateFlow<List<CustomerEntity>> = query
        .flatMapLatest { value ->
            if (value.isBlank()) {
                repository.observeCustomers()
            } else {
                repository.searchCustomers(value.trim())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun search(value: String) {
        query.value = value
    }

    fun addCustomer(
        name: String,
        phone: String,
        email: String,
        address: String,
        notes: String
    ) {
        val normalizedName = name.trim()
        val normalizedPhone = phone.trim()

        if (normalizedName.isEmpty()) {
            _error.value = "نام مشتری الزامی است."
            return
        }

        if (normalizedPhone.isEmpty()) {
            _error.value = "شماره تماس مشتری الزامی است."
            return
        }

        viewModelScope.launch {
            runCatching {
                repository.add(
                    CustomerEntity(
                        name = normalizedName,
                        phone = normalizedPhone,
                        email = email.trim(),
                        address = address.trim(),
                        notes = notes.trim()
                    )
                )
            }.onSuccess {
                _error.value = null
            }.onFailure { throwable ->
                _error.value = throwable.message ?: "ثبت مشتری انجام نشد."
            }
        }
    }

    fun updateCustomer(
        customer: CustomerEntity,
        name: String,
        phone: String,
        email: String,
        address: String,
        notes: String
    ) {
        val normalizedName = name.trim()
        val normalizedPhone = phone.trim()

        if (normalizedName.isEmpty() || normalizedPhone.isEmpty()) {
            _error.value = "نام و شماره تماس مشتری الزامی هستند."
            return
        }

        viewModelScope.launch {
            runCatching {
                repository.update(
                    customer.copy(
                        name = normalizedName,
                        phone = normalizedPhone,
                        email = email.trim(),
                        address = address.trim(),
                        notes = notes.trim()
                    )
                )
            }.onSuccess {
                _error.value = null
            }.onFailure { throwable ->
                _error.value = throwable.message ?: "ویرایش مشتری انجام نشد."
            }
        }
    }

    fun deleteCustomer(customer: CustomerEntity) {
        viewModelScope.launch {
            runCatching {
                repository.delete(customer)
            }.onSuccess {
                _error.value = null
            }.onFailure { throwable ->
                _error.value = throwable.message ?: "حذف مشتری انجام نشد."
            }
        }
    }

    fun clearError() {
        _error.value = null
    }
}
