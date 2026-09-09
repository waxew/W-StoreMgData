package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.SalesDao
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.data.local.entity.SaleItemEntity
import com.wstore.engine.data.model.SaleLineDraft
import com.wstore.engine.data.model.TopSellingProduct
import kotlinx.coroutines.flow.Flow

/**
 * مرز داده‌ای ماژول فروش.
 */
class SalesRepository(
    private val dao: SalesDao
) {
    fun observeSales(): Flow<List<SaleEntity>> = dao.observeSales()

    fun observeSalesForCustomer(customerId: Long): Flow<List<SaleEntity>> =
        dao.observeSalesForCustomer(customerId)

    fun observeSale(saleId: Long): Flow<SaleEntity?> =
        dao.observeSale(saleId)

    fun observeSaleItems(saleId: Long): Flow<List<SaleItemEntity>> =
        dao.observeSaleItems(saleId)

    fun observeTopSellingProducts(limit: Int = 5): Flow<List<TopSellingProduct>> =
        dao.observeTopSellingProducts(limit.coerceAtLeast(1))

    suspend fun createSale(
        customerId: Long?,
        lines: List<SaleLineDraft>,
        note: String
    ): Long = dao.createSale(customerId, lines, note)
}
