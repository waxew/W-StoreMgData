package com.wstore.engine.core.business.profile

/**
 * مقداردهی اولیه Runtime برای Business Profile.
 *
 * این کلاس مسئول اتصال Profile بارگذاری شده به Runtime است.
 * هیچ وابستگی به نوع فروشگاه ندارد و فقط Module و Feature فعال را مدیریت می‌کند.
 */
class BusinessProfileRuntimeInitializer(
    private val registry: BusinessProfileRegistry,
    private val moduleRegistry: BusinessModuleRegistry
) {

    /**
     * فعال‌سازی پروفایل کسب‌وکار انتخاب شده.
     */
    fun initialize(profile: BusinessProfile) {
        registry.register(profile)
        moduleRegistry.register(profile.modules)
    }
}
