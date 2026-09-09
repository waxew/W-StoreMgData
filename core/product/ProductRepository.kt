/*
================================================
نام فایل:
ProductRepository.kt

وظیفه:
تعریف قرارداد دسترسی به اطلاعات محصول.

این فایل فقط مسئول ارتباط لایه Domain با منبع داده است.
هیچ وابستگی به نوع کسب و کار ندارد.

توضیحات:
کسب و کارهایی مانند موبایل، پوشاک یا آرایشی
از طریق Business Profile و Module ها رفتار خود را مشخص می‌کنند.
================================================
*/

package com.wstore.engine.core.product

/**
 * قرارداد مدیریت داده های محصول.
 */
interface ProductRepository {
    fun getProducts(): List<ProductEntity>
    fun save(product: ProductEntity)
    fun delete(id: String)
}
