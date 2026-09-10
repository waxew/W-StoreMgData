package com.wstore.engine.ui.profile.mobile_store_001.sales

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * بخش پرداخت اختصاصی پروفایل فروشگاه موبایل.
 * در نسخه‌های بعدی به Payment Repository متصل می‌شود.
 */
@Composable
fun MobilePaymentSection(
    paymentMethod: String = "نقدی"
) {
    Text(text = "روش پرداخت: $paymentMethod")
}
