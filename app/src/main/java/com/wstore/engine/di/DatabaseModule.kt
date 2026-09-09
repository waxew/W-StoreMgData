package com.wstore.engine.di

import android.content.Context
import androidx.room.Room
import com.wstore.engine.data.local.database.AppDatabase
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
    fun provideProductDao(
        database: AppDatabase
    ) = database.productDao()
}
