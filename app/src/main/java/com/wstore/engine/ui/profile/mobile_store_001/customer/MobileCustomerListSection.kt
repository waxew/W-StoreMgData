package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * بخش لیست مشتریان پروفایل فروشگاه موبایل.
 *
 * این لایه فقط مسئول نمایش Profile UI است و منطق مشتری در Customer Core باقی می‌ماند.
 */
@Composable
fun MobileCustomerListSection(
    customers: List<String>
) {
    Column {
        customers.forEach { customer ->
            Text(text = customer)
        }
    }
}
