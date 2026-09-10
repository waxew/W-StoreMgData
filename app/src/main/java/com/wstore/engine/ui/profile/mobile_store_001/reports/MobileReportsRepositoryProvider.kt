package com.wstore.engine.ui.profile.mobile_store_001.reports

import com.wstore.engine.data.repository.ReportsRepository
import com.wstore.engine.data.repository.SalesRepository
import kotlinx.coroutines.flow.first
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

/**
 * تامین‌کننده واقعی داده گزارشات پروفایل فروشگاه موبایل.
 *
 * این Adapter داده را از Repositoryهای موجود می‌خواند و هیچ جدول یا مسیر نوشتنی جدیدی ایجاد نمی‌کند.
 * Core Repositoryها مستقل از Profile باقی می‌مانند.
 */
class MobileReportsRepositoryProvider @Inject constructor(
    private val reportsRepository: ReportsRepository,
    private val salesRepository: SalesRepository
) : MobileReportsDataProvider {

    override suspend fun todaySales(): String {
        val start = startOfTodayMillis()
        val total = salesRepository.observeSales()
            .first()
            .asSequence()
            .filter { sale -> sale.createdAt >= start }
            .sumOf { sale -> sale.totalAmount }
        return formatAmount(total)
    }

    override suspend fun monthlySales(): String {
        val start = startOfCurrentMonthMillis()
        val total = salesRepository.observeSales()
            .first()
            .asSequence()
            .filter { sale -> sale.createdAt >= start }
            .sumOf { sale -> sale.totalAmount }
        return formatAmount(total)
    }

    override suspend fun lowStockCount(): Int =
        reportsRepository.observeReports().first().lowStockCount

    override suspend fun inventoryValue(): String =
        formatAmount(reportsRepository.observeReports().first().inventoryRetailValue)

    private fun startOfTodayMillis(): Long = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    private fun startOfCurrentMonthMillis(): Long = Calendar.getInstance().apply {
        set(Calendar.DAY_OF_MONTH, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    private fun formatAmount(value: Double): String =
        String.format(Locale.getDefault(), "%,.0f", value)
}
