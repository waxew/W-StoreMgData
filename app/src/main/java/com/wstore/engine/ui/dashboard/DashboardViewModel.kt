package com.wstore.engine.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.repository.DashboardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel داشبورد مدیریتی.
 * آمار را به‌صورت واکنشی از DashboardRepository دریافت می‌کند.
 */
@HiltViewModel
class DashboardViewModel @Inject constructor(
    repository: DashboardRepository
) : ViewModel() {

    val uiState = repository.observeDashboard()
        .map { stats ->
            DashboardUiState(
                isLoading = false,
                customerCount = stats.customerCount,
                productCount = stats.productCount,
                salesCount = stats.salesCount,
                totalRevenue = stats.totalRevenue,
                lowStockCount = stats.lowStockCount,
                recentSales = stats.recentSales,
                error = null
            )
        }
        .catch { throwable ->
            emit(
                DashboardUiState(
                    isLoading = false,
                    error = throwable.message ?: "خواندن آمار داشبورد انجام نشد."
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DashboardUiState()
        )
}
