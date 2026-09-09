package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * نام فایل: InventoryTransactionEntity.kt
 * ماژول: Inventory
 * وظیفه: نگهداری تاریخچه ورود، خروج و اصلاح موجودی کالا.
 *
 * quantityDelta مقدار تغییر موجودی است:
 * مقدار مثبت = ورود کالا
 * مقدار منفی = خروج کالا
 */
@Entity(
    tableName = "inventory_transactions",
    indices = [
        Index(value = ["productId"]),
        Index(value = ["createdAt"])
    ]
)
data class InventoryTransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val productName: String,
    val productCode: String,
    val type: String,
    val quantityDelta: Int,
    val stockBefore: Int,
    val stockAfter: Int,
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
