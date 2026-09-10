package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

/**
 * نام فایل: ProductAttributeValueEntity.kt
 * ماژول: Dynamic Attribute Data
 * وظیفه: ذخیره مقدار فیلدهای پویا برای هر Product بدون تغییر ساختار ProductEntity برای هر صنف.
 *
 * مقدار همه Attributeها به صورت متن ذخیره می‌شود و نوع واقعی آن از Business Profile Schema خوانده می‌شود.
 * کلید ترکیبی productId + attributeKey یکتا است تا برای هر کالا از هر Attribute فقط یک مقدار وجود داشته باشد.
 */
@Entity(
    tableName = "product_attribute_values",
    primaryKeys = ["productId", "attributeKey"],
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["productId"]),
        Index(value = ["attributeKey"])
    ]
)
data class ProductAttributeValueEntity(
    val productId: Long,
    val attributeKey: String,
    val value: String,
    val updatedAt: Long = System.currentTimeMillis()
)
