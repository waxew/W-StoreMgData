package com.wstore.engine.ui.navigation

/**
 * نام فایل: AppUtilityRoute.kt
 * ماژول: App Shell / Navigation
 * وظیفه: تعریف مقصدهای مشترک Drawer که مستقل از نوع Business Profile هستند.
 */
sealed class AppUtilityRoute(
    val route: String,
    val title: String
) {
    data object Settings : AppUtilityRoute("app/settings", "تنظیمات")
    data object Notifications : AppUtilityRoute("app/notifications", "اعلان‌ها")
    data object About : AppUtilityRoute("app/about", "درباره نرم‌افزار")
    data object Contact : AppUtilityRoute("app/contact", "تماس با ما")
    data object Backup : AppUtilityRoute("app/backup", "پشتیبان‌گیری")
    data object Update : AppUtilityRoute("app/update", "بروزرسانی")

    companion object {
        val all: List<AppUtilityRoute> = listOf(
            Settings,
            Notifications,
            About,
            Contact,
            Backup,
            Update
        )
    }
}
