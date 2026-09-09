package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * سربرگ فروش.
 * نام مشتری به‌صورت Snapshot نگهداری می‌شود تا تاریخچه فروش با تغییر یا حذف مشتری قابل خواندن بماند.
 */
@Entity(
    tableName = "sales",
    indices = [
        Index(value = ["customerId"]),
        Index(value = ["createdAt"])
    ]
)
data class SaleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val customerId: Long? = null,
    val customerName: String,
    val totalAmount: Double,
    val note: String,
    val createdAt: Long = System.currentTimeMillis()
)
