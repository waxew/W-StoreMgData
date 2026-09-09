package com.wstore.engine.ui.navigation

/**
 * نام فایل: ModuleRouteRegistry.kt
 * ماژول: Navigation
 * وظیفه: تبدیل شناسه ماژول Runtime به Route مناسب.
 *
 * Dashboard هیچ تصمیمی درباره مقصد Featureها نمی‌گیرد و فقط شناسه ماژول را ارسال می‌کند.
 */
object ModuleRouteRegistry {

    fun routeFor(moduleId: String): String {
        return when (moduleId) {
            "product" -> ScreenRoute.Product.route
            else -> ScreenRoute.ModulePlaceholder.createRoute(moduleId)
        }
    }
}
