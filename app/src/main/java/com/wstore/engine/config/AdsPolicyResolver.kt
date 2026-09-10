package com.wstore.engine.config

/*
نام فایل:
AdsPolicyResolver.kt

ماژول:
Application Config Engine / Ads

وظیفه:
تصمیم‌گیری مرکزی درباره نمایش تبلیغات بر اساس App Config و وضعیت اشتراک کاربر.
این فایل هیچ SDK تبلیغاتی را به Core وابسته نمی‌کند و فقط Policy را محاسبه می‌کند.
*/

data class SubscriptionState(
    val isGuest: Boolean,
    val isVip: Boolean,
    val vipExpiresAtMillis: Long? = null
) {
    fun hasActiveVip(nowMillis: Long): Boolean =
        isVip && (vipExpiresAtMillis == null || vipExpiresAtMillis > nowMillis)
}

class AdsPolicyResolver {

    fun shouldShowAds(
        subscription: SubscriptionState,
        nowMillis: Long = System.currentTimeMillis(),
        config: AdsConfig = AppConfigRuntimeStore.current().ads
    ): Boolean {
        if (!config.enabled) return false

        val vipActive = subscription.hasActiveVip(nowMillis)
        if (vipActive && config.hideForVip) return false

        if (subscription.isGuest) {
            return config.showForGuest
        }

        val vipExpired = subscription.isVip && !vipActive
        if (vipExpired) {
            return config.showForExpiredSubscription
        }

        return true
    }
}
