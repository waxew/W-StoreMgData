package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.model.ProductDeleteResult
import kotlinx.coroutines.flow.Flow

/**
 * مرز داده‌ای ماژول کالا.
 * حذف کالا از این لایه به‌صورت کنترل‌شده انجام می‌شود تا سوابق فروش و فاکتور مخدوش نشوند.
 */
class ProductRepository(
    private val dao: ProductDao
) {
    fun observeProducts(): Flow<List<ProductEntity>> = dao.observeAll()

    suspend fun products(): List<ProductEntity> = dao.getAll()

    suspend fun getById(id: Long): ProductEntity? = dao.getById(id)

    suspend fun search(query: String): List<ProductEntity> = dao.search(query.trim())

    suspend fun add(product: ProductEntity): Long = dao.insert(product)

    suspend fun update(product: ProductEntity) {
        dao.update(product)
    }

    suspend fun delete(product: ProductEntity): ProductDeleteResult {
        val current = dao.getById(product.id) ?: return ProductDeleteResult.NotFound
        val saleReferences = dao.saleReferenceCount(current.id)
        val invoiceReferences = dao.invoiceReferenceCount(current.id)

        if (saleReferences > 0 || invoiceReferences > 0) {
            return ProductDeleteResult.BlockedByHistory(
                saleReferences = saleReferences,
                invoiceReferences = invoiceReferences
            )
        }

        dao.delete(current)
        return ProductDeleteResult.Deleted
    }
}
