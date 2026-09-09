package com.wstore.engine.data.model

/**
 * خط موقت فروش قبل از ثبت نهایی در دیتابیس.
 *
 * قیمت از Product فعلی در لحظه ثبت فروش Snapshot می‌شود تا UI نتواند مبلغ را دستکاری کند.
 */
data class SaleLineDraft(
    val productId: Long,
    val quantity: Int
)
