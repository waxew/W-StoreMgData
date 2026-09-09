package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.wstore.engine.data.local.entity.InventoryTransactionEntity
import kotlinx.coroutines.flow.Flow

/**
 * نام فایل: InventoryDao.kt
 * ماژول: Inventory
 * وظیفه: ثبت گردش موجودی و به‌روزرسانی اتمیک موجودی Product.
 */
@Dao
abstract class InventoryDao {

    @Query("SELECT * FROM inventory_transactions ORDER BY createdAt DESC, id DESC")
    abstract fun observeTransactions(): Flow<List<InventoryTransactionEntity>>

    @Query("SELECT stock FROM products WHERE id = :productId LIMIT 1")
    protected abstract suspend fun getCurrentStock(productId: Long): Int?

    @Query("SELECT name FROM products WHERE id = :productId LIMIT 1")
    protected abstract suspend fun getProductName(productId: Long): String?

    @Query("SELECT code FROM products WHERE id = :productId LIMIT 1")
    protected abstract suspend fun getProductCode(productId: Long): String?

    @Query("UPDATE products SET stock = :newStock WHERE id = :productId")
    protected abstract suspend fun updateProductStock(productId: Long, newStock: Int)

    @Insert
    protected abstract suspend fun insertTransaction(transaction: InventoryTransactionEntity)

    /**
     * تغییر موجودی و ثبت سند گردش در یک Transaction دیتابیس انجام می‌شود.
     * quantityDelta مثبت برای ورود و منفی برای خروج استفاده می‌شود.
     */
    @Transaction
    open suspend fun applyMovement(
        productId: Long,
        quantityDelta: Int,
        type: String,
        note: String
    ) {
        require(quantityDelta != 0) { "مقدار تغییر موجودی نمی‌تواند صفر باشد." }

        val stockBefore = getCurrentStock(productId)
            ?: error("کالای انتخاب‌شده در دیتابیس پیدا نشد.")
        val stockAfter = stockBefore + quantityDelta

        require(stockAfter >= 0) { "موجودی برای این خروج کافی نیست." }

        val productName = getProductName(productId)
            ?: error("نام کالای انتخاب‌شده پیدا نشد.")
        val productCode = getProductCode(productId)
            ?: error("کد کالای انتخاب‌شده پیدا نشد.")

        updateProductStock(productId, stockAfter)
        insertTransaction(
            InventoryTransactionEntity(
                productId = productId,
                productName = productName,
                productCode = productCode,
                type = type,
                quantityDelta = quantityDelta,
                stockBefore = stockBefore,
                stockAfter = stockAfter,
                note = note.trim()
            )
        )
    }
}
