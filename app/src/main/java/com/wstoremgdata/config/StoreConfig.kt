/*
================================================

File:
StoreConfig.kt

بخش:
Configuration مرکزی برنامه

وظیفه:
این فایل تمام اطلاعات قابل تغییر یک نسخه فروشگاهی را نگهداری می کند.

برای ساخت نسخه جدید از برنامه فقط این تنظیمات تغییر می کند.
منطق اصلی برنامه نباید وابسته به نام یا نوع فروشگاه باشد.

================================================
*/

package com.wstoremgdata.config

object StoreConfig {

    // نام برنامه نمایش داده شده به کاربر
    const val APP_NAME = "W Store Manager"

    // نام فروشگاه نمونه
    const val STORE_NAME = "فروشگاه نمونه"

    // توضیح کوتاه فروشگاه
    const val STORE_DESCRIPTION = "نرم افزار مدیریت فروشگاه"

    // نوع کسب و کار فعال
    const val BUSINESS_TYPE = "GENERAL_STORE"

    // رنگ اصلی برنامه
    const val PRIMARY_COLOR = "#000000"

    // نام فایل لوگو بدون پسوند
    const val LOGO_NAME = "store_logo"

    // تصویر داشبورد
    const val DASHBOARD_IMAGE = "dashboard_background"

    // فعال بودن ماژول ها
    const val ENABLE_INVENTORY = true
    const val ENABLE_CUSTOMER = true
    const val ENABLE_SUPPLIER = true
    const val ENABLE_REPORT = true
}
