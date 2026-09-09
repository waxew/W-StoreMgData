package com.wstore.engine.data.repository

import com.wstore.engine.data.model.ReportsRecentInventoryMovement
import com.wstore.engine.data.model.ReportsSnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * لایه خواندنی گزارش‌های مدیریتی.
 *
 * این Repository هیچ عملیات نوشتنی ندارد و داده‌های Sales، Product و Inventory را
 * بدون ایجاد جدول تکراری ترکیب می‌کند.
 */
class ReportsRepository(
    salesRepository: SalesRepository,
    productRepository: ProductRepository,
    inventoryRepository: InventoryRepository
) {
    private val sales = salesRepository.observeSales()
    private val products = productRepository.observeProducts()
    private val topSellingProducts = salesRepository.observeTopSellingProducts(TOP_PRODUCTS_LIMIT)
    private val inventoryMovements = inventoryRepository.observeTransactions()

    fun observeReports(): Flow<ReportsSnapshot> =
        combine(
            sales,
            products,
            topSellingProducts,
            inventoryMovements
        ) { saleList, productList, topProducts, movements ->
            val totalRevenue = saleList.sumOf { sale -> sale.totalAmount }
            val salesCount = saleList.size

            ReportsSnapshot(
                salesCount = salesCount,
                totalRevenue = totalRevenue,
                averageSale = if (salesCount == 0) 0.0 else totalRevenue / salesCount,
                productCount = productList.size,
                totalStockUnits = productList.sumOf { product -> product.stock },
                inventoryRetailValue = productList.sumOf { product -> product.price * product.stock },
                lowStockCount = productList.count { product ->
                    product.stock in 1..LOW_STOCK_THRESHOLD
                },
                outOfStockCount = productList.count { product -> product.stock == 0 },
                topSellingProducts = topProducts,
                recentInventoryMovements = movements
                    .take(RECENT_INVENTORY_LIMIT)
                    .map { movement ->
                        ReportsRecentInventoryMovement(
                            id = movement.id,
                            productName = movement.productName,
                            productCode = movement.productCode,
                            type = movement.type,
                            quantityDelta = movement.quantityDelta,
                            stockAfter = movement.stockAfter,
                            createdAt = movement.createdAt
                        )
                    }
            )
        }

    companion object {
        const val LOW_STOCK_THRESHOLD = 5
        const val TOP_PRODUCTS_LIMIT = 5
        const val RECENT_INVENTORY_LIMIT = 10
    }
}
