package com.wstore.engine.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * نام فایل: CustomerEntity.kt
 * ماژول: Customer Data
 * وظیفه: نگهداری اطلاعات پایه مشتری در دیتابیس محلی.
 *
 * تاریخچه خرید داخل این موجودیت تکرار نمی‌شود و در ادامه از ماژول Sales/Invoice
 * با شناسه مشتری به این رکورد متصل خواهد شد.
 */
@Entity(
    tableName = "customers",
    indices = [Index(value = ["phone"], unique = false)]
)
data class CustomerEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val phone: String,
    val email: String = "",
    val address: String = "",
    val notes: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
