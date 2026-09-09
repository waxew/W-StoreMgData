package com.wstore.engine.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * نام فایل: DashboardModuleCard.kt
 * ماژول: Dashboard
 * وظیفه: نمایش یک ماژول فعال و ارسال رویداد انتخاب آن به لایه بالاتر.
 *
 * این کامپوننت درباره فعال بودن ماژول یا مقصد Navigation تصمیم نمی‌گیرد.
 */
@Composable
fun DashboardModuleCard(
    moduleName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = moduleName,
            modifier = Modifier.padding(16.dp)
        )
    }
}
