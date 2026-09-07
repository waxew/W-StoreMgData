/*
====================================================
نام فایل: DatabaseModule.kt

وظیفه:
ماژول Dependency Injection برای دیتابیس.

این فایل مسئول ساخت وابستگی های Room است.

این لایه هیچ شناختی از نوع فروشگاه ندارد و فقط
زیرساخت ذخیره سازی را مدیریت می کند.
====================================================
*/

package com.wstore.engine.di

import android.content.Context
import androidx.room.Room
import com.wstore.engine.data.database.AppDatabase
import com.wstore.engine.data.database.dao.CustomerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "wstore_database"
        ).build()
    }

    @Provides
    fun provideCustomerDao(
        database: AppDatabase
    ): CustomerDao {
        return database.customerDao()
    }
}
