package com.wstore.engine.core.product

/*
================================================
نام فایل:
ProductPricing.kt

وظیفه:
تعریف مدل قیمت‌گذاری عمومی محصول.

این فایل به هیچ نوع کسب‌وکار خاصی وابسته نیست.
قیمت فروش، قیمت خرید و تخفیف در هسته محصول مدیریت می‌شوند.

موارد اختصاصی کسب‌وکار باید از Business Profile و Module ها خوانده شوند.
================================================
*/

data class ProductPricing(
    val purchasePrice: Double,
    val salePrice: Double,
    val discountPercent: Double = 0.0
)
