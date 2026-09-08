package com.wstore.engine.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * مدل ذخیره سازی مشتری در دیتابیس داخلی برنامه.
 *
 * Entity عمومی است و به نوع کسب و کار خاص وابسته نیست.
 */
@Entity(tableName = "customers")
data class CustomerRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fullName: String,
    val phoneNumber: String,
    val email: String?
)
