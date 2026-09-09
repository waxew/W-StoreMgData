package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.wstore.engine.data.local.entity.InventoryTransactionEntity
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.data.local.entity.SaleItemEntity
import com.wstore.engine.data.model.SaleLineDraft
import kotlinx.coroutines.flow.Flow

/**
 * نام فایل: SalesDao.kt
 * ماژول: Sales
 * وظیفه: ثبت اتمیک فروش، اقلام فروش، کاهش موجودی و سند گردش Inventory.
 */
@Dao
abstract class SalesDao {

    @Query("SELECT * FROM sales ORDER BY createdAt DESC, id DESC")
    abstract fun observeSales(): Flow<List<SaleEntity>>

    @Query("SELECT * FROM sales WHERE customerId = :customerId ORDER BY createdAt DESC, id DESC")
    abstract fun observeSalesForCustomer(customerId: Long): Flow<List<SaleEntity>>

    @Query("SELECT * FROM sales WHERE id = :saleId LIMIT 1")
    abstract fun observeSale(saleId: Long): Flow<SaleEntity?>

    @Query("SELECT * FROM sale_items WHERE saleId = :saleId ORDER BY id ASC")
    abstract fun observeSaleItems(saleId: Long): Flow<List<SaleItemEntity>>

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    protected abstract suspend fun getProduct(productId: Long): ProductEntity?

    @Query("SELECT name FROM customers WHERE id = :customerId LIMIT 1")
    protected abstract suspend fun getCustomerName(customerId: Long): String?

    @Insert
    protected abstract suspend fun insertSale(sale: SaleEntity): Long

    @Insert
    protected abstract suspend fun insertSaleItem(item: SaleItemEntity)

    @Query("UPDATE products SET stock = :newStock WHERE id = :productId")
    protected abstract suspend fun updateProductStock(productId: Long, newStock: Int)

    @Insert
    protected abstract suspend fun insertInventoryTransaction(transaction: InventoryTransactionEntity)

    /**
     * کل عملیات فروش در یک Transaction اجرا می‌شود.
     * در صورت کمبود موجودی یا خطای هر ردیف، هیچ بخشی از فروش ثبت نمی‌شود.
     */
    @Transaction
    open suspend fun createSale(
        customerId: Long?,
        lines: List<SaleLineDraft>,
        note: String
    ): Long {
        require(lines.isNotEmpty()) { "سبد فروش خالی است." }
        require(lines.all { it.quantity > 0 }) { "تعداد تمام اقلام فروش باید بیشتر از صفر باشد." }
        require(lines.map { it.productId }.distinct().size == lines.size) {
            "هر کالا فقط یک بار می‌تواند در سبد فروش وجود داشته باشد."
        }

        val customerName = if (customerId == null) {
            "مشتری آزاد"
        } else {
            getCustomerName(customerId)
                ?: error("مشتری انتخاب‌شده در دیتابیس پیدا نشد.")
        }

        val productsById = linkedMapOf<Long, ProductEntity>()
        var totalAmount = 0.0

        lines.forEach { line ->
            val product = getProduct(line.productId)
                ?: error("یکی از کالاهای سبد فروش در دیتابیس پیدا نشد.")
            require(product.stock >= line.quantity) {
                "موجودی کالای ${product.name} برای این فروش کافی نیست."
            }
            productsById[line.productId] = product
            totalAmount += product.price * line.quantity
        }

        val saleId = insertSale(
            SaleEntity(
                customerId = customerId,
                customerName = customerName,
                totalAmount = totalAmount,
                note = note.trim()
            )
        )

        lines.forEach { line ->
            val product = productsById.getValue(line.productId)
            val stockBefore = product.stock
            val stockAfter = stockBefore - line.quantity
            val lineTotal = product.price * line.quantity

            updateProductStock(product.id, stockAfter)

            insertSaleItem(
                SaleItemEntity(
                    saleId = saleId,
                    productId = product.id,
                    productName = product.name,
                    productCode = product.code,
                    quantity = line.quantity,
                    unitPrice = product.price,
                    lineTotal = lineTotal
                )
            )

            insertInventoryTransaction(
                InventoryTransactionEntity(
                    productId = product.id,
                    productName = product.name,
                    productCode = product.code,
                    type = TYPE_SALE,
                    quantityDelta = -line.quantity,
                    stockBefore = stockBefore,
                    stockAfter = stockAfter,
                    note = "فروش #$saleId${if (note.isBlank()) "" else " - ${note.trim()}"}"
                )
            )
        }

        return saleId
    }

    companion object {
        const val TYPE_SALE = "SALE"
    }
}
