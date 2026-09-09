package com.wstore.engine.core.business

/*
================================================
نام فایل:
BusinessConfiguration.kt

وظیفه:
نگهداری تنظیمات عمومی یک نمونه نرم‌افزار.

این بخش برای تنظیمات قابل تغییر بدون تغییر Core استفاده می‌شود.

================================================
*/

data class BusinessConfiguration(
    val themeId: String,
    val language: String,
    val currency: String
)
