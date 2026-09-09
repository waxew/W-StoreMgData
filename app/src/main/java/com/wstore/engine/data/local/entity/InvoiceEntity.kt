package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "invoices")
data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val saleId: Long,
    val invoiceNumber: String,
    val customerNameSnapshot: String,
    val totalAmount: Double,
    val createdAt: Long
)
