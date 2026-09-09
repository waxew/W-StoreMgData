package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.InventoryDao
import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.entity.InventoryTransactionEntity
import com.wstore.engine.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * نام فایل: InventoryRepository.kt
 * ماژول: Inventory
 * وظیفه: واسط بین UI/ViewModel و لایه Room برای گردش موجودی.
 */
class InventoryRepository(
    private val inventoryDao: InventoryDao,
    private val productDao: ProductDao
) {

    fun observeTransactions(): Flow<List<InventoryTransactionEntity>> =
        inventoryDao.observeTransactions()

    suspend fun products(): List<ProductEntity> = productDao.getAll()

    suspend fun stockIn(productId: Long, quantity: Int, note: String) {
        require(quantity > 0) { "تعداد ورود باید بیشتر از صفر باشد." }
        inventoryDao.applyMovement(
            productId = productId,
            quantityDelta = quantity,
            type = TYPE_IN,
            note = note
        )
    }

    suspend fun stockOut(productId: Long, quantity: Int, note: String) {
        require(quantity > 0) { "تعداد خروج باید بیشتر از صفر باشد." }
        inventoryDao.applyMovement(
            productId = productId,
            quantityDelta = -quantity,
            type = TYPE_OUT,
            note = note
        )
    }

    companion object {
        const val TYPE_IN = "IN"
        const val TYPE_OUT = "OUT"
    }
}
