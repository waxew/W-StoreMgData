package com.wstore.engine.data.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_attributes")
data class ProductAttributeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val attributeId: String,
    val value: String
)
