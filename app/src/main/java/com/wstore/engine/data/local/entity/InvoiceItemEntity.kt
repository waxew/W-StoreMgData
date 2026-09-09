package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * ردیف فاکتور.
 *
 * اطلاعات کالا و قیمت از SaleItem Snapshot می‌شوند تا فاکتور تاریخی مستقل از
 * تغییرات بعدی Product باقی بماند.
 */
@Entity(
    tableName = "invoice_items",
    indices = [
        Index(value = ["invoiceId"]),
        Index(value = ["saleItemId"]),
        Index(value = ["productId"])
    ]
)
data class InvoiceItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val invoiceId: Long,
    val saleItemId: Long,
    val productId: Long,
    val productNameSnapshot: String,
    val productCodeSnapshot: String,
    val quantity: Int,
    val unitPrice: Double,
    val lineTotal: Double
)
