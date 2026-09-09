package com.wstore.engine.data.model

import com.wstore.engine.data.local.entity.CustomerEntity
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.data.local.entity.SaleItemEntity

/**
 * خلاصه خریدهای یک مشتری.
 */
data class CustomerPurchaseSummary(
    val purchaseCount: Int,
    val totalAmount: Double,
    val lastPurchaseAt: Long?
)

/**
 * تاریخچه کامل خرید مشتری برای لایه UI.
 */
data class CustomerPurchaseHistory(
    val customer: CustomerEntity?,
    val sales: List<SaleEntity>,
    val summary: CustomerPurchaseSummary
)

/**
 * جزئیات یک فروش شامل سربرگ و اقلام Snapshot شده فروش.
 */
data class SaleDetail(
    val sale: SaleEntity?,
    val items: List<SaleItemEntity>
)
