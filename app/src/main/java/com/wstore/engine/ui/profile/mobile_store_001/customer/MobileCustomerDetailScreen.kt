package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * صفحه جزئیات مشتری پروفایل فروشگاه موبایل.
 *
 * این لایه فقط UI اختصاصی Profile است.
 * منطق مشتری در Customer Core و Repository باقی می‌ماند.
 */
@Composable
fun MobileCustomerDetailScreen(
    customerId: String
) {
    Text(
        text = "Customer Detail: $customerId"
    )
}
