package com.wstore.engine.ui.dashboard

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * کارت نمایش ماژول فعال کسب و کار.
 *
 * این کامپوننت فقط مسئول نمایش است و تصمیم فعال بودن
 * ماژول را از Runtime دریافت می‌کند.
 */
@Composable
fun DashboardModuleCard(moduleName: String) {
    Card {
        Text(text = moduleName)
    }
}
