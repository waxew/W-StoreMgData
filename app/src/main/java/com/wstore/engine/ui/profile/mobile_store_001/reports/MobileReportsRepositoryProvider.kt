package com.wstore.engine.ui.profile.mobile_store_001.reports

import javax.inject.Inject

/**
 * پیاده سازی اولیه تامین داده گزارشات پروفایل موبایل.
 *
 * این کلاس نقطه اتصال آینده بین Profile UI و Repositoryهای اصلی است.
 * Core Repositoryها بدون وابستگی به Profile باقی می مانند.
 */
class MobileReportsRepositoryProvider @Inject constructor() : MobileReportsDataProvider {

    override suspend fun todaySales(): String {
        return "0"
    }

    override suspend fun monthlySales(): String {
        return "0"
    }

    override suspend fun lowStockCount(): Int {
        return 0
    }

    override suspend fun profit(): String {
        return "0"
    }
}
