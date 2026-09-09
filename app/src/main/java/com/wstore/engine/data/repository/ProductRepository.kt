package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductRepository(
    private val dao: ProductDao
) {
    fun observeProducts(): Flow<List<ProductEntity>> = dao.observeAll()

    suspend fun products(): List<ProductEntity> = dao.getAll()

    suspend fun getById(id: Long): ProductEntity? = dao.getById(id)

    suspend fun search(query: String): List<ProductEntity> = dao.search(query)

    suspend fun add(product: ProductEntity) {
        dao.insert(product)
    }

    suspend fun update(product: ProductEntity) {
        dao.update(product)
    }

    suspend fun delete(product: ProductEntity) {
        dao.delete(product)
    }
}
