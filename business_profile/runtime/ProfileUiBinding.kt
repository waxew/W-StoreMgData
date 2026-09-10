package com.wstore.core.profile

/**
 * قرارداد اولیه اتصال Profile Runtime به UI.
 * هر صفحه باید قبل از نمایش Profile فعال را دریافت کند.
 */
data class ProfileUiBinding(
    val profileId: String,
    val uiProfile: String
)
