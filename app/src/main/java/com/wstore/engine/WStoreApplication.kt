/*
================================================
نام فایل:
WStoreApplication.kt

وظیفه:
کلاس Application اصلی برنامه اندروید.

این فایل نقطه شروع Dependency Injection و سرویس های عمومی برنامه است.

منطق کسب و کار در این فایل قرار نمی گیرد.
نوع فروشگاه از Business Profile دریافت خواهد شد.
================================================
*/

package com.wstore.engine

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WStoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // راه اندازی اولیه سرویس های عمومی برنامه در این بخش انجام می شود.
        // Business Profile و Module Registry در مراحل بعدی از این نقطه متصل می شوند.
    }
}
