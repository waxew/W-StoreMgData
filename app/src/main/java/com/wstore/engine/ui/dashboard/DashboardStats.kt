package com.wstore.engine.ui.dashboard

/**
 * مدل آماری داشبورد عملیاتی.
 * این مدل فقط خروجی محاسبات را نگهداری می‌کند و به دیتابیس وابسته نیست.
 */
data class DashboardStats(
    val customerCount: Int = 0,
    val productCount: Int = 0,
    val salesCount: Int = 0,
    val totalSalesAmount: Double = 0.0,
    val lowStockCount: Int = 0
)
