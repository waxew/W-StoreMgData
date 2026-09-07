/*
====================================================
نام فایل: RepositoryModule.kt

وظیفه:
اتصال Interface های Repository به پیاده سازی واقعی.

این لایه باعث می شود Presentation و Domain فقط
قراردادها را بشناسند و وابسته به Data Layer نباشند.
====================================================
*/

package com.wstore.engine.di

import com.wstore.engine.data.customer.CustomerRepositoryImpl
import com.wstore.engine.domain.customer.CustomerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCustomerRepository(
        repositoryImpl: CustomerRepositoryImpl
    ): CustomerRepository
}
