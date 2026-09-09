package com.wstore.engine.ui.customer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.model.CustomerPurchaseHistory
import com.wstore.engine.data.model.CustomerPurchaseSummary
import com.wstore.engine.data.repository.CustomerSalesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel جزئیات مشتری و تاریخچه خرید.
 */
@HiltViewModel
class CustomerDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: CustomerSalesRepository
) : ViewModel() {

    private val customerId: Long =
        checkNotNull(savedStateHandle[CustomerModule.CUSTOMER_ID_ARGUMENT]) {
            "شناسه مشتری برای صفحه جزئیات ارسال نشده است."
        }

    val history: StateFlow<CustomerPurchaseHistory> =
        repository.observeCustomerHistory(customerId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CustomerPurchaseHistory(
                customer = null,
                sales = emptyList(),
                summary = CustomerPurchaseSummary(
                    purchaseCount = 0,
                    totalAmount = 0.0,
                    lastPurchaseAt = null
                )
            )
        )
}
