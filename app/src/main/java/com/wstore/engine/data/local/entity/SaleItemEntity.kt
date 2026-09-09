package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * ردیف کالای فروش.
 * نام، کد و قیمت کالا Snapshot می‌شوند تا اسناد قدیمی با تغییر Product مخدوش نشوند.
 */
@Entity(
    tableName = "sale_items",
    indices = [
        Index(value = ["saleId"]),
        Index(value = ["productId"])
    ]
)
data class SaleItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val saleId: Long,
    val productId: Long,
    val productName: String,
    val productCode: String,
    val quantity: Int,
    val unitPrice: Double,
    val lineTotal: Double
)
