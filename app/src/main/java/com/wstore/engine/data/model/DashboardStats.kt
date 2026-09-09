package com.wstore.engine.data.model

/**
 * نمای خواندنی آمار داشبورد.
 * این مدل فقط برای گزارش‌گیری است و هیچ داده‌ای را در دیتابیس تغییر نمی‌دهد.
 */
data class DashboardStats(
    val customerCount: Int,
    val productCount: Int,
    val salesCount: Int,
    val totalRevenue: Double,
    val lowStockCount: Int,
    val recentSales: List<DashboardRecentSale>
)

/**
 * Snapshot سبک برای نمایش فروش‌های اخیر در داشبورد.
 */
data class DashboardRecentSale(
    val id: Long,
    val customerName: String,
    val totalAmount: Double,
    val createdAt: Long
)
