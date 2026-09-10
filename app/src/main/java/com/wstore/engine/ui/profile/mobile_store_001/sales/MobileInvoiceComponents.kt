package com.wstore.engine.ui.profile.mobile_store_001.sales

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * کامپوننت‌های پایه فاکتور پروفایل موبایل.
 */
@Composable
fun MobileInvoiceSummary(total: String) {
    Text(text = "مبلغ کل: $total")
}
