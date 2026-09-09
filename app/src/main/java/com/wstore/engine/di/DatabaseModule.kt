package com.wstore.engine.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.wstore.engine.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * مهاجرت نسخه 1 به 2.
 * جدول Product بدون تغییر باقی می‌ماند و فقط جدول Customer اضافه می‌شود.
 */
private val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `customers` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `name` TEXT NOT NULL,
                `phone` TEXT NOT NULL,
                `email` TEXT NOT NULL,
                `address` TEXT NOT NULL,
                `notes` TEXT NOT NULL,
                `createdAt` INTEGER NOT NULL
            )
            """.trimIndent()
        )

        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_customers_phone` ON `customers` (`phone`)"
        )
    }
}

/**
 * مهاجرت نسخه 2 به 3.
 * جدول تاریخچه گردش Inventory اضافه می‌شود و جداول Product و Customer بدون حذف باقی می‌مانند.
 */
private val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `inventory_transactions` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `productId` INTEGER NOT NULL,
                `productName` TEXT NOT NULL,
                `productCode` TEXT NOT NULL,
                `type` TEXT NOT NULL,
                `quantityDelta` INTEGER NOT NULL,
                `stockBefore` INTEGER NOT NULL,
                `stockAfter` INTEGER NOT NULL,
                `note` TEXT NOT NULL,
                `createdAt` INTEGER NOT NULL
            )
            """.trimIndent()
        )

        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_inventory_transactions_productId` ON `inventory_transactions` (`productId`)"
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_inventory_transactions_createdAt` ON `inventory_transactions` (`createdAt`)"
        )
    }
}

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
        )
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()
    }

    @Provides
    fun provideProductDao(
        database: AppDatabase
    ) = database.productDao()

    @Provides
    fun provideCustomerDao(
        database: AppDatabase
    ) = database.customerDao()

    @Provides
    fun provideInventoryDao(
        database: AppDatabase
    ) = database.inventoryDao()
}
