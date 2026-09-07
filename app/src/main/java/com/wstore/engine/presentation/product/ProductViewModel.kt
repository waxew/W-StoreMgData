package com.wstore.engine.presentation.product

/*
================================================
نام فایل:
ProductViewModel.kt

وظیفه:
اتصال UI محصول به منطق برنامه.

مسیر:
UI -> ViewModel -> UseCase -> Repository

هیچ منطق مربوط به نوع فروشگاه در این فایل قرار نمی‌گیرد.
================================================
*/

class ProductViewModel {
    val state = ProductUiState()
}
