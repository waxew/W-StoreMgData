package com.wstore.core.profile

/**
 * نقطه دسترسی مشترک برای UI و Backend.
 * هدف جلوگیری از وابستگی مستقیم ماژول ها به فایل Profile است.
 */
class ProfileRuntimeFacade(
    private val provider: ProfileRuntimeProvider
) {

    fun activeProfile(): ProfileDefinition? {
        return provider.getActiveProfile()
    }

    fun hasFeature(feature: String): Boolean {
        return activeProfile()?.features?.contains(feature) == true
    }

    fun hasModule(module: String): Boolean {
        return activeProfile()?.modules?.contains(module) == true
    }
}
