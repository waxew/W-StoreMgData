package com.wstore.engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.wstore.engine.runtime.BusinessRuntimeInitializer
import com.wstore.engine.ui.dashboard.DashboardScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)

        BusinessRuntimeInitializer.initialize()

        setContent {
            DashboardScreen()
        }
    }
}
