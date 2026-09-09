package com.wstore.engine.ui.dashboard

/**
 * وضعیت نمایشی داشبورد مدیریتی.
 * داده‌ها از Repository تامین می‌شوند و UI فقط نمایش‌دهنده است.
 */
data class DashboardUiState(
    val isLoading: Boolean = false,
    val customerCount: Int = 0,
    val productCount: Int = 0,
    val salesCount: Int = 0,
    val totalRevenue: Long = 0L,
    val lowStockCount: Int = 0
)
