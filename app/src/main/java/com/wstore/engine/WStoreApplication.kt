/*
================================================
نام فایل:
WStoreApplication.kt

وظیفه:
کلاس Application اصلی برنامه اندروید.

این فایل نقطه شروع راه‌اندازی سرویس‌های عمومی برنامه است.

در آینده مسئولیت راه‌اندازی موارد زیر را خواهد داشت:
- Business Profile Loader
- Module Registry
- Database
- Dependency Injection

نکته مهم:
این فایل نباید شامل منطق کسب‌وکار باشد.
نوع فروشگاه از Business Profile دریافت می‌شود.
================================================
*/

package com.wstore.engine

import android.app.Application

class WStoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // راه‌اندازی اولیه سرویس‌های عمومی برنامه در این بخش انجام می‌شود.
    }
}
