package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * مدل ذخیره سازی مقدارهای Dynamic Attribute.
 * این جدول برای تمام پروفایل ها مشترک است و به صنعت خاصی وابسته نیست.
 */
@Entity(tableName = "attribute_values")
data class AttributeValueEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val ownerId: String,
    val ownerType: String,
    val attributeKey: String,
    val value: String
)
