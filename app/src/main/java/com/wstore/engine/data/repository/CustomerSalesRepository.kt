package com.wstore.engine.data.repository

import com.wstore.engine.data.model.CustomerPurchaseHistory
import com.wstore.engine.data.model.CustomerPurchaseSummary
import com.wstore.engine.data.model.SaleDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

/**
 * Repository ترکیبی برای تاریخچه خرید مشتری و جزئیات فروش.
 *
 * این لایه داده جدیدی ذخیره نمی‌کند؛ اطلاعات را از Customer و Sales موجود ترکیب می‌کند
 * تا Snapshotهای تاریخی فروش دست‌نخورده باقی بمانند.
 */
class CustomerSalesRepository @Inject constructor(
    private val customerRepository: CustomerRepository,
    private val salesRepository: SalesRepository
) {

    fun observeCustomerHistory(customerId: Long): Flow<CustomerPurchaseHistory> =
        combine(
            customerRepository.observeById(customerId),
            salesRepository.observeSalesForCustomer(customerId)
        ) { customer, sales ->
            CustomerPurchaseHistory(
                customer = customer,
                sales = sales,
                summary = CustomerPurchaseSummary(
                    purchaseCount = sales.size,
                    totalAmount = sales.sumOf { it.totalAmount },
                    lastPurchaseAt = sales.maxOfOrNull { it.createdAt }
                )
            )
        }

    fun observeSaleDetail(saleId: Long): Flow<SaleDetail> =
        combine(
            salesRepository.observeSale(saleId),
            salesRepository.observeSaleItems(saleId)
        ) { sale, items ->
            SaleDetail(
                sale = sale,
                items = items
            )
        }
}
