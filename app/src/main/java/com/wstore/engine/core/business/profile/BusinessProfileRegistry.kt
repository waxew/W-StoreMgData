package com.wstore.engine.core.business.profile

/*
====================================================
نام فایل: BusinessProfileRegistry.kt

وظیفه:
نگهداری Business Profile فعال در زمان اجرای برنامه.

این لایه باعث می شود هسته برنامه بدون شناخت نوع فروشگاه
بتواند تنظیمات فعال را دریافت کند.
====================================================
*/

class BusinessProfileRegistry {

    private var activeProfile: Any? = null

    fun register(profile: Any) {
        activeProfile = profile
    }

    fun getActiveProfile(): Any? {
        return activeProfile
    }
}
