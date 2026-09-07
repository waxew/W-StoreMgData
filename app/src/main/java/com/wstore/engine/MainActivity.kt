package com.wstore.engine

/*
================================================
نام فایل:
MainActivity.kt

وظیفه:
نقطه شروع اجرای برنامه اندروید.

در نسخه نهایی:
1- Business Profile فعال بارگذاری می‌شود.
2- Module های فعال شناسایی می‌شوند.
3- UI بر اساس تنظیمات کسب‌وکار ساخته می‌شود.

این فایل نباید شامل منطق کسب‌وکار باشد.
================================================
*/

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // در مراحل بعد رابط کاربری اصلی اینجا متصل می‌شود.
            // اطلاعات ظاهری از Business Profile دریافت خواهد شد.
        }
    }
}
