package com.wstore.engine.ui.profile.mobile_store_001.reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * کامپوننت‌های گزارش پروفایل فروشگاه موبایل.
 * این لایه فقط UI Profile است و داده از Report Core تزریق می‌شود.
 */
@Composable
fun MobileReportCard(
    title: String,
    value: String
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Text(text = value)
        }
    }
}
