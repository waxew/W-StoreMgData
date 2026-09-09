package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.wstore.engine.data.local.entity.InvoiceEntity
import com.wstore.engine.data.local.entity.InvoiceItemEntity
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.data.local.entity.SaleItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * نام فایل: InvoiceDao.kt
 * ماژول: Invoice
 * وظیفه: ساخت اتمیک فاکتور از Sales و خواندن فهرست/جزئیات فاکتورها.
 */
@Dao
abstract class InvoiceDao {

    @Query("SELECT * FROM invoices ORDER BY createdAt DESC, id DESC")
    abstract fun observeInvoices(): Flow<List<InvoiceEntity>>

    @Query(
        "SELECT s.* FROM sales s LEFT JOIN invoices i ON i.saleId = s.id " +
            "WHERE i.id IS NULL ORDER BY s.createdAt DESC, s.id DESC"
    )
    abstract fun observeSalesWithoutInvoice(): Flow<List<SaleEntity>>

    @Query("SELECT * FROM invoice_items WHERE invoiceId = :invoiceId ORDER BY id ASC")
    abstract fun observeInvoiceItems(invoiceId: Long): Flow<List<InvoiceItemEntity>>

    @Query("SELECT * FROM invoices WHERE id = :invoiceId LIMIT 1")
    abstract suspend fun getInvoice(invoiceId: Long): InvoiceEntity?

    @Query("SELECT * FROM invoices WHERE saleId = :saleId LIMIT 1")
    protected abstract suspend fun getInvoiceBySaleId(saleId: Long): InvoiceEntity?

    @Query("SELECT * FROM sales WHERE id = :saleId LIMIT 1")
    protected abstract suspend fun getSale(saleId: Long): SaleEntity?

    @Query("SELECT * FROM sale_items WHERE saleId = :saleId ORDER BY id ASC")
    protected abstract suspend fun getSaleItems(saleId: Long): List<SaleItemEntity>

    @Insert
    protected abstract suspend fun insertInvoice(invoice: InvoiceEntity): Long

    @Insert
    protected abstract suspend fun insertInvoiceItems(items: List<InvoiceItemEntity>)

    /**
     * از یک Sale ثبت‌شده یک فاکتور immutable می‌سازد.
     * اگر برای Sale قبلاً فاکتور ساخته شده باشد، همان شناسه برگردانده می‌شود.
     */
    @Transaction
    open suspend fun createFromSale(saleId: Long): Long {
        getInvoiceBySaleId(saleId)?.let { return it.id }

        val sale = getSale(saleId)
            ?: error("فروش انتخاب‌شده در دیتابیس پیدا نشد.")
        val saleItems = getSaleItems(saleId)
        require(saleItems.isNotEmpty()) { "فروش انتخاب‌شده هیچ قلمی ندارد." }

        val invoiceId = insertInvoice(
            InvoiceEntity(
                saleId = sale.id,
                invoiceNumber = buildInvoiceNumber(sale.id),
                customerNameSnapshot = sale.customerName,
                totalAmount = sale.totalAmount,
                noteSnapshot = sale.note,
                saleCreatedAt = sale.createdAt
            )
        )

        insertInvoiceItems(
            saleItems.map { item ->
                InvoiceItemEntity(
                    invoiceId = invoiceId,
                    saleItemId = item.id,
                    productId = item.productId,
                    productNameSnapshot = item.productName,
                    productCodeSnapshot = item.productCode,
                    quantity = item.quantity,
                    unitPrice = item.unitPrice,
                    lineTotal = item.lineTotal
                )
            }
        )

        return invoiceId
    }

    private fun buildInvoiceNumber(saleId: Long): String =
        "INV-${saleId.toString().padStart(8, '0')}"
}
