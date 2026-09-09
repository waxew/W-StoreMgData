package com.wstore.engine.ui.dashboard

import com.wstore.engine.data.model.DashboardRecentSale

/**
 * وضعیت نمایشی داشبورد مدیریتی.
 * داده‌ها از Repository تامین می‌شوند و UI فقط نمایش‌دهنده است.
 */
data class DashboardUiState(
    val isLoading: Boolean = true,
    val customerCount: Int = 0,
    val productCount: Int = 0,
    val salesCount: Int = 0,
    val totalRevenue: Double = 0.0,
    val lowStockCount: Int = 0,
    val recentSales: List<DashboardRecentSale> = emptyList(),
    val error: String? = null
)
