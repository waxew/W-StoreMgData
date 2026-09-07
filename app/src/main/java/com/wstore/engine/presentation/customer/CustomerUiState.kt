package com.wstore.engine.presentation.customer

// وضعیت نمایشی بخش مشتریان
// این فایل فقط وضعیت UI را نگهداری می‌کند و هیچ وابستگی به نوع کسب و کار ندارد.
data class CustomerUiState(
    val isLoading: Boolean = false,
    val customers: List<String> = emptyList(),
    val errorMessage: String? = null
)
