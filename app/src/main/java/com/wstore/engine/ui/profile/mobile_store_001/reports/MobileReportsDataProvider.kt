package com.wstore.engine.ui.profile.mobile_store_001.reports

/**
 * قرارداد تامین داده گزارشات پروفایل موبایل.
 *
 * این Interface در لایه UI Profile قرار دارد تا اتصال به
 * Repositoryهای واقعی بدون وابستگی مستقیم ViewModel به Core انجام شود.
 */
interface MobileReportsDataProvider {
    suspend fun todaySales(): String
    suspend fun monthlySales(): String
    suspend fun lowStockCount(): Int
    suspend fun profit(): String
}
