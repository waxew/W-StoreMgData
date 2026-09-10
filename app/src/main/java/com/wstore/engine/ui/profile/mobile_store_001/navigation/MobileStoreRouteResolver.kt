package com.wstore.engine.ui.profile.mobile_store_001.navigation

/**
 * Resolver مسیرهای UI پروفایل فروشگاه موبایل.
 *
 * این لایه فقط انتخاب Route های Profile UI را انجام می‌دهد
 * و منطق Navigation Core را تغییر نمی‌دهد.
 */
object MobileStoreRouteResolver {

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
