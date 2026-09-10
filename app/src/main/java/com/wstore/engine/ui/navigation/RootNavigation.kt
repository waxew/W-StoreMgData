package com.wstore.engine.ui.navigation

import androidx.navigation.NavHostController

/**
 * نام فایل: RootNavigation.kt
 * ماژول: Navigation
 * وظیفه: اجرای سیاست Back برای جابه‌جایی بین بخش‌های اصلی برنامه.
 *
 * وقتی کاربر از Dashboard/Drawer/منوی اصلی وارد یک Root Feature جدید می‌شود، Feature قبلی
 * نباید در Stack باقی بماند. بنابراین Back از Root جدید مستقیماً به Home برمی‌گردد.
 *
 * مثال مورد انتظار:
 * Home → T → انتخاب C از منوی اصلی → Back → Home
 */
fun NavHostController.navigateToRoot(route: String) {
    navigate(route) {
        popUpTo(ScreenRoute.Dashboard.route) {
            inclusive = false
        }
        launchSingleTop = true
    }
}
