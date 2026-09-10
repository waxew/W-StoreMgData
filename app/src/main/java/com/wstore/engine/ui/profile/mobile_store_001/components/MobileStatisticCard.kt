package com.wstore.engine.ui.profile.mobile_store_001.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * کامپوننت کارت آماری اختصاصی پروفایل فروشگاه موبایل
 *
 * برای نمایش اطلاعات داشبورد مانند فروش، موجودی و مشتریان استفاده می‌شود.
 */
@Composable
fun MobileStatisticCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Text(text = title)
        Text(text = value)
    }
}
