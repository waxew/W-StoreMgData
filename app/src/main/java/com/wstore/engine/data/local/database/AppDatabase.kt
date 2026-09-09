package com.wstore.engine.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wstore.engine.data.local.dao.CustomerDao
import com.wstore.engine.data.local.dao.InventoryDao
import com.wstore.engine.data.local.dao.InvoiceDao
import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.dao.SalesDao
import com.wstore.engine.data.local.entity.CustomerEntity
import com.wstore.engine.data.local.entity.InventoryTransactionEntity
import com.wstore.engine.data.local.entity.InvoiceEntity
import com.wstore.engine.data.local.entity.InvoiceItemEntity
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.data.local.entity.SaleItemEntity

/**
 * دیتابیس اصلی برنامه.
 *
 * نسخه 2 ماژول Customer را به ساختار موجود Product اضافه کرد.
 * نسخه 3 جدول تاریخچه گردش Inventory را اضافه کرد.
 * نسخه 4 سربرگ و اقلام Sales را اضافه کرد.
 * نسخه 5 سربرگ و اقلام Invoice را اضافه می‌کند.
 * داده‌های نسخه‌های قبلی با Migration نگهداری می‌شوند و دیتابیس تخریب نمی‌شود.
 */
@Database(
    entities = [
        ProductEntity::class,
        CustomerEntity::class,
        InventoryTransactionEntity::class,
        SaleEntity::class,
        SaleItemEntity::class,
        InvoiceEntity::class,
        InvoiceItemEntity::class
    ],
    version = 5,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
    abstract fun inventoryDao(): InventoryDao
    abstract fun salesDao(): SalesDao
    abstract fun invoiceDao(): InvoiceDao
}
