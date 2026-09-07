/*
================================================
نام فایل:
ModuleLoader.kt

بخش:
Core Module Engine

وظیفه:
مدیریت بارگذاری ماژول های فعال بر اساس Business Profile.

این بخش تصمیم می گیرد چه قابلیت هایی در برنامه فعال باشند.

مثال:
Mobile Store می تواند Warranty داشته باشد.
Boutique می تواند Product Variant داشته باشد.

Core فقط Module را می شناسد، نه نوع کسب و کار را.
================================================
*/

package com.wstoremgdata.core.module

/**
 * بارگذاری و بررسی فعال بودن قابلیت ها
 */
class ModuleLoader {

    fun isEnabled(moduleId: String, enabledModules: List<String>): Boolean {
        return enabledModules.contains(moduleId)
    }
}
