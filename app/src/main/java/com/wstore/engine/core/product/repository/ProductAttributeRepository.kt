package com.wstore.engine.core.product.repository

interface ProductAttributeRepository {
    suspend fun saveAttributes(productId: Long, attributes: List<Pair<String, String>>)
    suspend fun getAttributesByProductId(productId: Long): List<Pair<String, String>>
    suspend fun deleteAttributes(productId: Long)
}
