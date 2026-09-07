package com.wstore.engine.core.product

/**
 * مدل Runtime محصول.
 *
 * ویژگی‌های اختصاصی هر کسب و کار از Dynamic Attribute Engine می‌آیند.
 */
data class ProductRuntime(
    val id: String,
    val name: String,
    val attributes: List<ProductAttributeValue> = emptyList()
)
