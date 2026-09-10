package com.wstore.engine.ui.profile.mobile_store_001.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel گزارشات پروفایل فروشگاه موبایل.
 *
 * این لایه فقط هماهنگ کننده UI Profile است.
 * منطق محاسبه گزارش در Core/Repository قرار می گیرد.
 */
@HiltViewModel
class MobileReportsViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(MobileReportsUiState())
    val state: StateFlow<MobileReportsUiState> = _state.asStateFlow()

    /**
     * در مرحله اتصال Repository مقادیر واقعی از Sales,
     * Inventory و Product دریافت خواهند شد.
     */
    fun refresh() {
        _state.value = _state.value.copy(
            isReady = true
        )
    }
}

data class MobileReportsUiState(
    val todaySales: String = "0",
    val monthlySales: String = "0",
    val lowStockCount: Int = 0,
    val profit: String = "0",
    val isReady: Boolean = false
)
