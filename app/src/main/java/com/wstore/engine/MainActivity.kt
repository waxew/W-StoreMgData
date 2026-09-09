package com.wstore.engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wstore.engine.runtime.BusinessRuntimeInitializer
import com.wstore.engine.ui.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

/**
 * نقطه ورود اصلی برنامه.
 * ابتدا Business Profile فعال می‌شود و سپس Navigation Graph برنامه اجرا می‌گردد.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)

        // فعال‌سازی Profile قبل از ساخت UI و Navigation.
        BusinessRuntimeInitializer.initialize()

        setContent {
            AppNavHost()
        }
    }
}
