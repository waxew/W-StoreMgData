/*
================================================
نام فایل:
AttributeSchema.kt

بخش:
Core Attribute Engine

وظیفه:
تعریف ساختار ویژگی های پویا برای موجودیت های سیستم.

این فایل مشخص می کند هر موجودیت چه Attribute هایی می تواند داشته باشد.

مثال:
Product در موبایل می تواند Storage و Color داشته باشد.
Product در پوشاک می تواند Size و Material داشته باشد.

این اطلاعات نباید به صورت فیلد ثابت داخل Core Entity قرار بگیرند.
================================================
*/

package com.wstore.engine.core.attribute

/**
 * تعریف مجموعه ویژگی های قابل استفاده برای یک موجودیت
 */
data class AttributeSchema(
    val entityType: String,
    val attributes: List<AttributeDefinition>
)
