package com.wstore.engine.core.attribute

/*
================================================
نام فایل:
AttributeDefinition.kt

بخش:
Dynamic Attribute Engine

وظیفه:
تعریف ویژگی های متغیر موجودیت ها بدون تغییر Core.

نمونه استفاده:
Color، Size، Storage، Volume، Expiration

این موارد نباید به صورت Field ثابت داخل Entity اصلی قرار بگیرند.
================================================
*/

data class AttributeDefinition(
    val id: String,
    val name: String,
    val type: String,
    val required: Boolean = false
)
