package com.wstore.engine.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wstore.engine.runtime.RuntimeModuleService

/**
 * داشبورد بر اساس Business Profile فعال.
 *
 * این صفحه Module ها را از Runtime می‌گیرد و
 * منطق کسب و کار را داخل UI هاردکد نمی‌کند.
 */
@Composable
fun DashboardScreen() {
    val modules = RuntimeModuleService.getActiveModules()

    Column {
        Text("W-StoreMgData Dashboard")
        modules.forEach { module ->
            Text(module)
        }
    }
}
