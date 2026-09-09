package com.wstore.engine.ui.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wstore.engine.data.model.ReportsSnapshot
import com.wstore.engine.data.repository.ReportsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

/**
 * ViewModel صفحه گزارش‌ها.
 * UI فقط State دریافت می‌کند و هیچ Query یا محاسبه مدیریتی را مستقیم اجرا نمی‌کند.
 */
@HiltViewModel
class ReportsViewModel @Inject constructor(
    reportsRepository: ReportsRepository
) : ViewModel() {

    val uiState: StateFlow<ReportsUiState> = reportsRepository
        .observeReports()
        .map { snapshot ->
            ReportsUiState(
                isLoading = false,
                snapshot = snapshot,
                error = null
            )
        }
        .catch { throwable ->
            emit(
                ReportsUiState(
                    isLoading = false,
                    error = throwable.message ?: "خواندن گزارش‌ها انجام نشد."
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReportsUiState(isLoading = true)
        )
}

data class ReportsUiState(
    val isLoading: Boolean = false,
    val snapshot: ReportsSnapshot = ReportsSnapshot(),
    val error: String? = null
)
