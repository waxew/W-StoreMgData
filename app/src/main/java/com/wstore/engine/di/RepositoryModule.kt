package com.wstore.engine.di

import com.wstore.engine.data.local.dao.CustomerDao
import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.repository.CustomerRepository
import com.wstore.engine.data.repository.ProductRepository
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
}
