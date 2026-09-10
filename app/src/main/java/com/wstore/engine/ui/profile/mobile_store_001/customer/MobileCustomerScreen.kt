package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * صفحه مشتریان پروفایل فروشگاه موبایل.
 *
 * این لایه فقط UI پروفایل است و منطق Customer Core را تغییر نمی‌دهد.
 * اتصال به ViewModel و Repository در مرحله Binding انجام می‌شود.
 */
@Composable
fun MobileCustomerScreen() {
    Text(text = "مشتریان فروشگاه موبایل")
}
