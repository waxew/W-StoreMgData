package com.wstore.engine.ui.navigation

/**
 * نام فایل: ScreenRoute.kt
 * ماژول: Navigation
 * وظیفه: تعریف Routeهای اصلی برنامه بدون وابستگی Dashboard به جزئیات Featureها.
 */
sealed class ScreenRoute(val route: String) {
    data object Dashboard : ScreenRoute("dashboard")
    data object Product : ScreenRoute("product")
    data object ModulePlaceholder : ScreenRoute("module/{moduleId}") {
        fun createRoute(moduleId: String): String = "module/$moduleId"
    }
}
