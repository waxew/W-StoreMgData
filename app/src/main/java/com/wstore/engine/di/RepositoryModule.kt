package com.wstore.engine.di

import com.wstore.engine.data.local.dao.CustomerDao
import com.wstore.engine.data.local.dao.InventoryDao
import com.wstore.engine.data.local.dao.InvoiceDao
import com.wstore.engine.data.local.dao.ProductAttributeDao
import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.dao.SalesDao
import com.wstore.engine.data.local.database.AppDatabase
import com.wstore.engine.data.repository.CustomerRepository
import com.wstore.engine.data.repository.DashboardRepository
import com.wstore.engine.data.repository.InventoryRepository
import com.wstore.engine.data.repository.InvoiceRepository
import com.wstore.engine.data.repository.ProductAttributeRepository
import com.wstore.engine.data.repository.ProductProfileRepository
import com.wstore.engine.data.repository.ProductRepository
import com.wstore.engine.data.repository.ReportsRepository
import com.wstore.engine.data.repository.SalesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * نام فایل: RepositoryModule.kt
 * ماژول: Dependency Injection
 * وظیفه: ساخت Repositoryهای Data Layer بدون قرار دادن منطق تجاری در UI.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideProductRepository(
        dao: ProductDao
    ): ProductRepository {
        return ProductRepository(dao)
    }

    /** Repository جدا برای خواندن/نوشتن مقادیر Dynamic Attribute کالا. */
    @Provides
    @Singleton
    fun provideProductAttributeRepository(
        dao: ProductAttributeDao
    ): ProductAttributeRepository {
        return ProductAttributeRepository(dao)
    }

    /**
     * Repository ترکیبی Product + Dynamic Attribute را در یک Room Transaction ذخیره می‌کند.
     */
    @Provides
    @Singleton
    fun provideProductProfileRepository(
        database: AppDatabase,
        productDao: ProductDao,
        productAttributeRepository: ProductAttributeRepository
    ): ProductProfileRepository {
        return ProductProfileRepository(
            database = database,
            productDao = productDao,
            attributeRepository = productAttributeRepository
        )
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(
        dao: CustomerDao
    ): CustomerRepository {
        return CustomerRepository(dao)
    }

    @Provides
    @Singleton
    fun provideInventoryRepository(
        inventoryDao: InventoryDao,
        productDao: ProductDao
    ): InventoryRepository {
        return InventoryRepository(inventoryDao, productDao)
    }

    @Provides
    @Singleton
    fun provideSalesRepository(
        salesDao: SalesDao
    ): SalesRepository {
        return SalesRepository(salesDao)
    }

    @Provides
    @Singleton
    fun provideInvoiceRepository(
        invoiceDao: InvoiceDao
    ): InvoiceRepository {
        return InvoiceRepository(invoiceDao)
    }

    /**
     * DashboardRepository فقط Repositoryهای موجود را ترکیب می‌کند و به DAO مستقیم وابسته نیست.
     */
    @Provides
    @Singleton
    fun provideDashboardRepository(
        customerRepository: CustomerRepository,
        productRepository: ProductRepository,
        salesRepository: SalesRepository
    ): DashboardRepository {
        return DashboardRepository(
            customerRepository = customerRepository,
            productRepository = productRepository,
            salesRepository = salesRepository
        )
    }

    /**
     * ReportsRepository یک Read Model روی لایه‌های موجود است و هیچ مسیر نوشتنی ایجاد نمی‌کند.
     */
    @Provides
    @Singleton
    fun provideReportsRepository(
        salesRepository: SalesRepository,
        productRepository: ProductRepository,
        inventoryRepository: InventoryRepository
    ): ReportsRepository {
        return ReportsRepository(
            salesRepository = salesRepository,
            productRepository = productRepository,
            inventoryRepository = inventoryRepository
        )
    }
}
