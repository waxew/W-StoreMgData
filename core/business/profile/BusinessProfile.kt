package com.wstore.engine.core.business.profile

/**
 * مدل پایه پروفایل کسب و کار.
 *
 * این کلاس اطلاعات متغیر کسب و کار را از هسته نرم افزار جدا می کند.
 * هسته برنامه نباید مستقیما نوع فروشگاه را بشناسد.
 *
 * مثال:
 * فروشگاه موبایل، بوتیک یا لوازم آرایشی فقط یک Profile متفاوت هستند.
 */
data class BusinessProfile(
    val id: String,
    val name: String,
    val enabledModules: List<String>,
    val enabledAttributes: List<String>
)
