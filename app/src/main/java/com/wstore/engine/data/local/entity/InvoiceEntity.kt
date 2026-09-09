package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * سربرگ فاکتور.
 *
 * اطلاعات اصلی فروش به‌صورت Snapshot نگهداری می‌شوند تا فاکتورهای قبلی با تغییر
 * Customer یا داده‌های جاری فروش مخدوش نشوند. هر Sale فقط یک Invoice دارد.
 */
@Entity(
    tableName = "invoices",
    indices = [
        Index(value = ["saleId"], unique = true),
        Index(value = ["createdAt"])
    ]
)
data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val saleId: Long,
    val invoiceNumber: String,
    val customerNameSnapshot: String,
    val totalAmount: Double,
    val noteSnapshot: String,
    val saleCreatedAt: Long,
    val createdAt: Long = System.currentTimeMillis()
)
