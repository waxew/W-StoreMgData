package com.wstore.engine.core.module

/*
================================================
نام فایل:
ModuleDefinition.kt

بخش:
Core Module Engine

وظیفه:
تعریف استاندارد یک قابلیت نرم افزاری مستقل.

این فایل نباید شامل نام هیچ کسب و کاری باشد.
ماژول هایی مانند گارانتی، رزرو، تعمیرات و تبلیغات
از این ساختار استفاده می کنند.

ارتباط:
Business Profile مشخص می کند چه Module هایی فعال باشند.
================================================
*/

data class ModuleDefinition(
    val id: String,
    val name: String,
    val enabled: Boolean = true
)
