package com.wstore.engine.ui.imei_warranty

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AttributeLookupBindings {

    @Binds
    @Singleton
    abstract fun bindAttributeLookupRepository(
        impl: AttributeLookupRepositoryImpl
    ): AttributeLookupRepository
}
