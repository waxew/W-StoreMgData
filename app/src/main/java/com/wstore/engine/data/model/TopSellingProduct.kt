package com.wstore.engine.data.model

/**
 * خروجی تجمیعی فروش کالا برای گزارش مدیریتی.
 * این مدل Entity نیست و داده تکراری در دیتابیس ایجاد نمی‌کند.
 */
data class TopSellingProduct(
    val productId: Long,
    val productName: String,
    val quantitySold: Long,
    val revenue: Double
)
