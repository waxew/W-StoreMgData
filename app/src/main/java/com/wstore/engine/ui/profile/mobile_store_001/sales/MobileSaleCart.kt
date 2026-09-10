package com.wstore.engine.ui.profile.mobile_store_001.sales

/**
 * مدل اولیه سبد فروش پروفایل فروشگاه موبایل
 *
 * این مدل فقط برای لایه UI Profile ایجاد شده و جایگزین Core Sales Model نیست.
 */
data class MobileSaleCart(
    val items: List<MobileSaleItem> = emptyList(),
    val totalAmount: Long = 0L
)

/** آیتم‌های فاکتور فروش موبایل */
data class MobileSaleItem(
    val productId: Long,
    val title: String,
    val quantity: Int,
    val price: Long
)
