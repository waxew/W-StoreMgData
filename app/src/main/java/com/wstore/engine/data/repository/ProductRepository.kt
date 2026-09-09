package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.entity.ProductEntity

class ProductRepository(
    private val dao: ProductDao
) {
    suspend fun products(): List<ProductEntity> = dao.getAll()

    suspend fun add(product: ProductEntity) {
        dao.insert(product)
    }
}
