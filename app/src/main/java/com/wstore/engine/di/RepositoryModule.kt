package com.wstore.engine.di

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
        dao: com.wstore.engine.data.local.dao.ProductDao
    ): ProductRepository {
        return ProductRepository(dao)
    }
}
