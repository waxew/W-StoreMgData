package com.wstore.engine.core.business.profile

/**
 * مدل اصلی پروفایل کسب و کار.
 *
 * این کلاس فقط اطلاعات تنظیماتی کسب و کار را نگهداری می کند.
 * هسته برنامه نباید به نوع کسب و کار وابسته باشد.
 */
data class BusinessProfile(
    val id: String,
    val name: String,
    val packageName: String,
    val modules: List<BusinessModuleConfig> = emptyList(),
    val features: List<String> = emptyList()
)
