package com.wstore.engine.core.business

/*
================================================
نام فایل:
BusinessCapability.kt

وظیفه:
تعریف قابلیت‌های فعال یک Business Profile.

این فایل مشخص می‌کند یک نسخه از برنامه چه Module هایی را فعال دارد.

مثال:
- Warranty
- Repair
- Delivery

================================================
*/

data class BusinessCapability(
    val enabledModules: List<String>,
    val enabledAttributes: List<String>
)
