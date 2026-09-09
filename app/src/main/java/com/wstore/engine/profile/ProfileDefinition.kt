package com.wstore.engine.profile

/*
نام فایل:
ProfileDefinition.kt

ماژول:
Business Profile Engine

وظیفه:
قرارداد استاندارد و مستقل از نوع کسب‌وکار برای تعریف Business Profile.
تمام Profileها از فایل JSON خوانده می‌شوند و Core نباید نام کسب‌وکار مشخصی را بشناسد.

نکته توسعه:
برای اضافه کردن Profile جدید، در حالت عادی فقط یک فایل JSON جدید اضافه می‌شود و این مدل تغییر نمی‌کند.
*/

data class ProfileDefinition(
    val schemaVersion: Int,
    val id: String,
    val name: String,
    val businessType: String,
    val enabled: Boolean,
    val modules: List<ProfileModuleDefinition>,
    val features: List<String>,
    val attributes: List<ProfileAttributeDefinition>,
    val uiProfile: ProfileUiDefinition,
    val theme: ProfileThemeDefinition,
    val assets: ProfileAssetDefinition
) {
    /** شناسه ماژول‌هایی که برای Profile فعلی فعال هستند. */
    fun enabledModuleIds(): List<String> =
        modules.filter { it.enabled }.map { it.id }
}

data class ProfileModuleDefinition(
    val id: String,
    val enabled: Boolean
)

data class ProfileAttributeDefinition(
    val key: String,
    val label: String,
    val type: String,
    val required: Boolean = false,
    val visible: Boolean = true,
    val options: List<String> = emptyList()
)

data class ProfileUiDefinition(
    val dashboardVariant: String,
    val productCardVariant: String,
    val heroAssetKey: String,
    val navigationVariant: String = "default"
)

data class ProfileThemeDefinition(
    val name: String,
    val primaryToken: String,
    val backgroundToken: String,
    val useDynamicColor: Boolean = false
)

data class ProfileAssetDefinition(
    val iconKey: String,
    val logoKey: String,
    val dashboardHeroKey: String,
    val emptyStateKey: String
)
