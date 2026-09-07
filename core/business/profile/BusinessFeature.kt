package com.wstore.engine.core.business.profile

/**
 * تعریف قابلیت های قابل فعال شدن برای هر Business Profile.
 *
 * قابلیت ها توسط تنظیمات کسب و کار فعال می شوند
 * و منطق آنها نباید داخل هسته عمومی هاردکد شود.
 */
data class BusinessFeature(
    val key: String,
    val enabled: Boolean
)
