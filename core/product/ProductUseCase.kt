package com.wstore.engine.core.product

/*
================================================
نام فایل:
ProductUseCase.kt

وظیفه:
تعریف عملیات منطقی محصول.

این لایه بین UI و Repository قرار می‌گیرد.

عملیات محصول:
- ایجاد
- ویرایش
- حذف
- جستجو
- مدیریت ویژگی‌ها

این منطق نباید وابسته به نوع فروشگاه باشد.
================================================
*/

class ProductUseCase(
    private val repository: ProductRepository
) {
    fun create(product: ProductEntity) {
        repository.save(product)
    }

    fun delete(id: String) {
        repository.delete(id)
    }
}
