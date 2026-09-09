package com.wstore.engine.data.model

/**
 * Snapshot سبک برای نمایش آخرین گردش‌های موجودی در صفحه گزارش‌ها.
 */
data class ReportsRecentInventoryMovement(
    val id: Long,
    val productName: String,
    val productCode: String,
    val type: String,
    val quantityDelta: Int,
    val stockAfter: Int,
    val createdAt: Long
)
