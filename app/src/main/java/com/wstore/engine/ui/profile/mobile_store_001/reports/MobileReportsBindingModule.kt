package com.wstore.engine.ui.profile.mobile_store_001.reports

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * اتصال Hilt بین قرارداد گزارشات Profile و Adapter واقعی Repository.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class MobileReportsBindingModule {

    @Binds
    @Singleton
    abstract fun bindMobileReportsDataProvider(
        implementation: MobileReportsRepositoryProvider
    ): MobileReportsDataProvider
}
