package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * کامپوننت‌های اختصاصی پروفایل فروشگاه موبایل.
 *
 * این لایه فقط ظاهر Profile UI را نگهداری می‌کند.
 * منطق مشتری در Customer Core باقی می‌ماند.
 */

@Composable
fun MobileCustomerCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Text(text = title)
    }
}
