package com.wstore.engine.ui.profile.mobile_store_001.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel گزارشات پروفایل فروشگاه موبایل.
 *
 * این لایه فقط State و چرخه بارگذاری UI را مدیریت می‌کند؛
 * داده و محاسبات از MobileReportsDataProvider تامین می‌شوند.
 */
@HiltViewModel
class MobileReportsViewModel @Inject constructor(
    private val dataProvider: MobileReportsDataProvider
) : ViewModel() {

    private val _state = MutableStateFlow(MobileReportsUiState(isLoading = true))
    val state: StateFlow<MobileReportsUiState> = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)

            runCatching {
                MobileReportsUiState(
                    todaySales = dataProvider.todaySales(),
                    monthlySales = dataProvider.monthlySales(),
                    lowStockCount = dataProvider.lowStockCount(),
                    inventoryValue = dataProvider.inventoryValue(),
                    isLoading = false,
                    isReady = true,
                    error = null
                )
            }.onSuccess { loaded ->
                _state.value = loaded
            }.onFailure { throwable ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    isReady = false,
                    error = throwable.message ?: "خواندن گزارش‌های فروشگاه انجام نشد."
                )
            }
        }
    }
}

data class MobileReportsUiState(
    val todaySales: String = "0",
    val monthlySales: String = "0",
    val lowStockCount: Int = 0,
    val inventoryValue: String = "0",
    val isLoading: Boolean = false,
    val isReady: Boolean = false,
    val error: String? = null
)
