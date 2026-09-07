/*
================================================
نام فایل:
ProductVariant.kt

وظیفه:
مدیریت حالت های مختلف یک محصول.

مثال:
- رنگ
- سایز
- مدل
- ظرفیت

این فایل جایگزین ایجاد فیلدهای ثابت برای هر کسب و کار است.
اطلاعات واقعی از Attribute Schema دریافت می‌شود.
================================================
*/

package com.wstore.engine.core.product

/**
 * مدل پایه برای تنوع محصول.
 */
data class ProductVariant(
    val id: String,
    val productId: String,
    val attributes: Map<String, String>
)
