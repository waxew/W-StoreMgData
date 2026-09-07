package com.wstore.engine

/*
================================================
نام فایل:
MainActivity.kt

وظیفه:
نقطه شروع اجرای برنامه اندروید.

این فایل فقط مسئول راه‌اندازی UI است.
منطق کسب‌وکار، مشتری، محصول و فروش در اینجا قرار نمی‌گیرد.

جریان اجرا:
MainActivity
    ↓
AppTheme
    ↓
AppNavigation
    ↓
Screens
================================================
*/

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wstore.engine.ui.navigation.AppNavigation
import com.wstore.engine.ui.theme.AppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // ظاهر برنامه از لایه Theme مدیریت می‌شود.
            AppTheme {

                // مدیریت مسیر صفحات برنامه در Navigation انجام می‌شود.
                AppNavigation()
            }
        }
    }
}
