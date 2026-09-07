package com.wstore.engine.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

// کامپوننت عمومی نمایش حالت خالی
// قابل استفاده در تمام بخش های برنامه بدون وابستگی به نوع کسب و کار
@Composable
fun EmptyStateComponent(message: String) {
    Text(text = message)
}
