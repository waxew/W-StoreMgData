package com.wstore.engine.ui.profile.mobile_store_001.components

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * کارت دسترسی سریع ماژول‌های فروشگاه موبایل
 * مانند کالا، فروش، انبار و مشتریان
 */
@Composable
fun MobileModuleCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Text(text = title)
    }
}
