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

/**
 * مهاجرت نسخه 3 به 4.
 * ساختار Sales اضافه می‌شود و Product/Customer/Inventory دست‌نخورده باقی می‌مانند.
 */
private val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `sales` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `customerId` INTEGER,
                `customerName` TEXT NOT NULL,
                `totalAmount` REAL NOT NULL,
                `note` TEXT NOT NULL,
                `createdAt` INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_sales_customerId` ON `sales` (`customerId`)"
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_sales_createdAt` ON `sales` (`createdAt`)"
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `sale_items` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `saleId` INTEGER NOT NULL,
                `productId` INTEGER NOT NULL,
                `productName` TEXT NOT NULL,
                `productCode` TEXT NOT NULL,
                `quantity` INTEGER NOT NULL,
                `unitPrice` REAL NOT NULL,
                `lineTotal` REAL NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_sale_items_saleId` ON `sale_items` (`saleId`)"
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_sale_items_productId` ON `sale_items` (`productId`)"
        )
    }
}

/**
 * مهاجرت نسخه 4 به 5.
 * جداول Invoice به‌صورت افزایشی اضافه می‌شوند و Product/Customer/Inventory/Sales حفظ می‌شوند.
 */
private val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `invoices` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `saleId` INTEGER NOT NULL,
                `invoiceNumber` TEXT NOT NULL,
                `customerNameSnapshot` TEXT NOT NULL,
                `totalAmount` REAL NOT NULL,
                `noteSnapshot` TEXT NOT NULL,
                `saleCreatedAt` INTEGER NOT NULL,
                `createdAt` INTEGER NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            "CREATE UNIQUE INDEX IF NOT EXISTS `index_invoices_saleId` ON `invoices` (`saleId`)"
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_invoices_createdAt` ON `invoices` (`createdAt`)"
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `invoice_items` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `invoiceId` INTEGER NOT NULL,
                `saleItemId` INTEGER NOT NULL,
                `productId` INTEGER NOT NULL,
                `productNameSnapshot` TEXT NOT NULL,
                `productCodeSnapshot` TEXT NOT NULL,
                `quantity` INTEGER NOT NULL,
                `unitPrice` REAL NOT NULL,
                `lineTotal` REAL NOT NULL
            )
            """.trimIndent()
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_invoice_items_invoiceId` ON `invoice_items` (`invoiceId`)"
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_invoice_items_saleItemId` ON `invoice_items` (`saleItemId`)"
        )
        db.execSQL(
            "CREATE INDEX IF NOT EXISTS `index_invoice_items_productId` ON `invoice_items` (`productId`)"
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
            .addMigrations(
                MIGRATION_1_2,
                MIGRATION_2_3,
                MIGRATION_3_4,
                MIGRATION_4_5
            )
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

    @Provides
    fun provideSalesDao(
        database: AppDatabase
    ) = database.salesDao()

    @Provides
    fun provideInvoiceDao(
        database: AppDatabase
    ) = database.invoiceDao()
}
