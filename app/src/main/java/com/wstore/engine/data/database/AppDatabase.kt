package com.wstore.engine.data.database

/*
====================================================
نام فایل: AppDatabase.kt

وظیفه:
تعریف دیتابیس اصلی برنامه.

این فایل فقط مسئول اتصال لایه Data به ذخیره سازی داخلی است.
هیچ وابستگی به نوع کسب و کار ندارد.

Business Profile و Attribute Engine مشخص می کنند
چه داده هایی در برنامه فعال باشند.
====================================================
*/

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wstore.engine.data.database.dao.CustomerDao
import com.wstore.engine.data.database.entity.CustomerRoomEntity

@Database(
    entities = [CustomerRoomEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    // دسترسی به اطلاعات مشتری
    abstract fun customerDao(): CustomerDao
}
