package com.wstore.engine.data.repository

import com.wstore.engine.data.model.DashboardRecentSale
import com.wstore.engine.data.model.DashboardStats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

/**
 * لایه گزارش‌گیری داشبورد.
 *
 * داده‌های Customer، Product و Sales را فقط به‌صورت خواندنی ترکیب می‌کند و هیچ مسیر
 * نوشتنی یا میان‌بری برای تغییر موجودی/فروش ایجاد نمی‌کند.
 */
class DashboardRepository(
    customerRepository: CustomerRepository,
    productRepository: ProductRepository,
    salesRepository: SalesRepository
) {
    private val customers = customerRepository.observeCustomers()
    private val products = productRepository.observeProducts()
    private val sales = salesRepository.observeSales()

    fun observeDashboard(): Flow<DashboardStats> =
        combine(customers, products, sales) { customerList, productList, saleList ->
            DashboardStats(
                customerCount = customerList.size,
                productCount = productList.size,
                salesCount = saleList.size,
                totalRevenue = saleList.sumOf { sale -> sale.totalAmount },
                lowStockCount = productList.count { product ->
                    product.stock <= LOW_STOCK_THRESHOLD
                },
                recentSales = saleList
                    .take(RECENT_SALES_LIMIT)
                    .map { sale ->
                        DashboardRecentSale(
                            id = sale.id,
                            customerName = sale.customerName,
                            totalAmount = sale.totalAmount,
                            createdAt = sale.createdAt
                        )
                    }
            )
        }

    companion object {
        const val LOW_STOCK_THRESHOLD = 5
        const val RECENT_SALES_LIMIT = 5
    }
}
