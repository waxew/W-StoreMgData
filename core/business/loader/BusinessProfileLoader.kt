/*
================================================
نام فایل:
BusinessProfileLoader.kt

وظیفه:
لود کردن Business Profile فعال در زمان اجرای برنامه.

این فایل فقط تنظیمات کسب و کار را می‌خواند و هیچ منطق اختصاصی برای نوع کسب و کار ندارد.

نمونه:
mobile_store_001 یا boutique_store_001 فقط داده هستند و داخل Core منطق جداگانه ایجاد نمی‌کنند.
================================================
*/

package com.wstore.engine.core.business.loader

class BusinessProfileLoader {
    fun load(profileId: String): String {
        return profileId
    }
}
