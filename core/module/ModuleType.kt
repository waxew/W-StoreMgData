/*
================================================
نام فایل:
ModuleType.kt

بخش:
Core Module Engine

وظیفه:
تعریف دسته بندی ماژول های سیستم.

ماژول ها قابلیت هایی هستند که دارای منطق و رفتار هستند.

نمونه:
Warranty
Repair
Delivery
Appointment

این فایل نباید شامل نوع کسب و کار باشد.
================================================
*/

package com.wstore.engine.core.module

/**
 * انواع کلی ماژول های سیستم
 */
enum class ModuleType {
    CORE,
    BUSINESS,
    EXTENSION
}
