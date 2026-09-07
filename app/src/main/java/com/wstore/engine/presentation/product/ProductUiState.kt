package com.wstore.engine.presentation.product

/*
================================================
نام فایل:
ProductUiState.kt

وظیفه:
نگهداری وضعیت نمایش بخش محصولات.

این لایه فقط وضعیت UI را مدیریت می‌کند.
اطلاعات متغیر کسب و کار توسط Attribute Engine تامین می‌شود.
================================================
*/

 data class ProductUiState(
    val isLoading: Boolean = false,
    val message: String? = null
)
