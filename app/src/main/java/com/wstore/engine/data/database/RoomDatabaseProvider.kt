package com.wstore.engine.data.database

import android.content.Context
import androidx.room.Room

/*
====================================================
نام فایل: RoomDatabaseProvider.kt

وظیفه:
ساخت و نگهداری نمونه اصلی Room Database.

این کلاس فقط زیرساخت ذخیره سازی را مدیریت می کند.
اطلاعات کسب و کار، Business Profile و Attribute ها
نباید در این لایه قرار بگیرند.
====================================================
*/

object RoomDatabaseProvider {

    private var database: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "w_store_database"
            ).build().also {
                database = it
            }
        }
    }
}
