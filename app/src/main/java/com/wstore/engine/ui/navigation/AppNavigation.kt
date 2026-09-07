package com.wstore.engine.ui.navigation

// این فایل مسئول مدیریت مسیرهای اصلی برنامه است.
// تمام Route ها عمومی هستند و به نوع کسب و کار وابسته نیستند.
// اطلاعاتی مثل نوع فروشگاه از Business Profile دریافت خواهد شد.

class AppNavigation {

    // اولین صفحه شروع برنامه
    fun startDestination(): String {
        return ScreenRoutes.DASHBOARD
    }

    // محل توسعه NavHost در Jetpack Compose Navigation
    // صفحات آینده:
    // Dashboard
    // Customer List
    // Customer Detail
    // Product
    // Inventory
}
