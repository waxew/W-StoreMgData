package com.wstore.engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wstore.engine.runtime.ApplicationRuntimeInitializer
import com.wstore.engine.ui.navigation.AppNavHost
import com.wstore.engine.ui.profile.ProfileThemeHost
import dagger.hilt.android.AndroidEntryPoint

/**
 * نام فایل: MainActivity.kt
 * ماژول: Application Entry
 * وظیفه: راه‌اندازی Config/Profile Runtime، اعمال Theme پروفایل فعال و سپس ساخت Navigation Graph برنامه.
 *
 * هیچ اطلاعات ثابت برنامه یا نوع کسب‌وکار در این Activity نگهداری نمی‌شود.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)

        // App Config و Business Profile قبل از ساخت UI از فایل‌های مرکزی خوانده می‌شوند.
        ApplicationRuntimeInitializer.initialize(applicationContext)

        setContent {
            ProfileThemeHost {
                AppNavHost()
            }
        }
    }
}
