package com.wstore.engine.profile

/*
نام فایل:
ProfileDefinition.kt

ماژول:
Business Profile Engine

وظیفه:
مدل استاندارد تعریف یک کسب و کار.
تمام پروفایل‌ها باید از این قرارداد پیروی کنند تا اضافه کردن پروفایل جدید نیاز به تغییر Core نداشته باشد.
*/

data class ProfileDefinition(
    val id: String,
    val name: String,
    val enabled: Boolean,
    val modules: List<String>,
    val features: List<String>,
    val attributes: List<String>,
    val uiProfile: String,
    val theme: String,
    val assets: String
)
