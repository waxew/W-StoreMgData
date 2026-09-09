/*
================================================
نام فایل:
ProductAttributeRelation.kt

وظیفه:
اتصال کالا به Attribute های پویا.

مثال:
Mobile Profile:
Storage = 256GB
Color = Black

Boutique Profile:
Size = XL
Color = White

این اطلاعات داخل ProductEntity قرار نمی‌گیرند.

================================================
*/

package com.wstore.engine.core.product

/**
 * ارتباط بین محصول و ویژگی های پویا.
 */
data class ProductAttributeRelation(
    val productId: String,
    val attributeId: String,
    val value: String
)
