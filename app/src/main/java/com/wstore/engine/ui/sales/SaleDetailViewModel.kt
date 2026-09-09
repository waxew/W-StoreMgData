package com.wstore.engine.ui.sales

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.model.SaleDetail
import com.wstore.engine.data.repository.CustomerSalesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel جزئیات فروش و اقلام Snapshot شده آن.
 */
@HiltViewModel
class SaleDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    repository: CustomerSalesRepository
) : ViewModel() {

    private val saleId: Long =
        checkNotNull(savedStateHandle[SalesModule.SALE_ID_ARGUMENT]) {
            "شناسه فروش برای صفحه جزئیات ارسال نشده است."
        }

    val detail: StateFlow<SaleDetail> =
        repository.observeSaleDetail(saleId).stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SaleDetail(sale = null, items = emptyList())
        )
}
