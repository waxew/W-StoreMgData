package com.wstore.engine.ui.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.runtime.RuntimeModuleService

/**
 * نام فایل: DashboardScreen.kt
 * ماژول: Dashboard
 * وظیفه: نمایش ماژول‌های فعال Business Profile و ارسال انتخاب کاربر به Navigation.
 *
 * تصمیم فعال بودن Featureها از Runtime می‌آید و Routeها خارج از Dashboard مدیریت می‌شوند.
 */
@Composable
fun DashboardScreen(
    onModuleSelected: (String) -> Unit
) {
    val modules = RuntimeModuleService.getActiveModules()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("W-StoreMgData Dashboard")

        modules.forEach { module ->
            DashboardModuleCard(
                moduleName = module,
                onClick = { onModuleSelected(module) }
            )
        }
    }
}
