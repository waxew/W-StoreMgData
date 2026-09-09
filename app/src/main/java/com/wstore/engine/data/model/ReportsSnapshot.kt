package com.wstore.engine.data.model

/**
 * Snapshot واکنشی صفحه گزارش‌ها.
 */
data class ReportsSnapshot(
    val salesCount: Int = 0,
    val totalRevenue: Double = 0.0,
    val averageSale: Double = 0.0,
    val productCount: Int = 0,
    val totalStockUnits: Int = 0,
    val inventoryRetailValue: Double = 0.0,
    val lowStockCount: Int = 0,
    val outOfStockCount: Int = 0,
    val topSellingProducts: List<TopSellingProduct> = emptyList(),
    val recentInventoryMovements: List<ReportsRecentInventoryMovement> = emptyList()
)
