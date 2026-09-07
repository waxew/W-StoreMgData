/*
================================================
نام فایل:
RuntimeModuleLoader.kt

وظیفه:
مدیریت فعال‌سازی Module ها در زمان اجرای برنامه.

این فایل بررسی می‌کند چه قابلیت‌هایی بر اساس Business Profile باید فعال شوند.

Module ها قابلیت نرم افزاری هستند، نه نوع کسب و کار.
================================================
*/

package com.wstore.engine.core.module.runtime

class RuntimeModuleLoader {
    fun load(modules: List<String>): List<String> {
        return modules
    }
}
