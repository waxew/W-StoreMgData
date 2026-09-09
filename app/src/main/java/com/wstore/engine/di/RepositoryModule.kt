package com.wstore.engine.di

import com.wstore.engine.data.local.dao.CustomerDao
import com.wstore.engine.data.local.dao.InventoryDao
import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.dao.SalesDao
import com.wstore.engine.data.repository.CustomerRepository
import com.wstore.engine.data.repository.InventoryRepository
import com.wstore.engine.data.repository.ProductRepository
import com.wstore.engine.data.repository.SalesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
}
