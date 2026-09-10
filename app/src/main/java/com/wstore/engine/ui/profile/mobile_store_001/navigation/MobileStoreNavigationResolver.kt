package com.wstore.engine.ui.profile.mobile_store_001.navigation

/**
 * پل ارتباطی بین Profile Runtime و Navigation پروفایل فروشگاه موبایل.
 *
 * این کلاس تصمیم می‌گیرد که کدام مسیرهای UI برای پروفایل فعال ارائه شوند.
 * منطق Core Navigation در این بخش تغییر نمی‌کند.
 */
object MobileStoreNavigationResolver {

    fun resolve(profileId: String): List<String> {
        return when (profileId) {
            "mobile_store_001" -> listOf(
                MobileStoreProfileNavigation.DASHBOARD,
                MobileStoreProfileNavigation.PRODUCTS,
                MobileStoreProfileNavigation.INVENTORY,
                MobileStoreProfileNavigation.SALES,
                MobileStoreProfileNavigation.CUSTOMERS,
                MobileStoreProfileNavigation.REPORTS
            )
            else -> emptyList()
        }
    }
}
