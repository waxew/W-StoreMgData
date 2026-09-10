package com.wstore.engine.ui.profile.mobile_store_001.reports

/**
 * قرارداد تامین داده گزارشات پروفایل موبایل.
 *
 * UI فقط این قرارداد را می‌شناسد و محاسبات واقعی از Repositoryهای Core تامین می‌شوند.
 * سود واقعی عمداً در این قرارداد محاسبه نمی‌شود چون Product Core هنوز بهای تمام‌شده ندارد؛
 * به‌جای عدد ساختگی، ارزش فروش موجودی نمایش داده می‌شود.
 */
interface MobileReportsDataProvider {
    suspend fun todaySales(): String
    suspend fun monthlySales(): String
    suspend fun lowStockCount(): Int
    suspend fun inventoryValue(): String
}
