package com.wstore.engine.ui.profile.mobile_store_001.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * نمایش یک Attribute پویا در صفحات محصول پروفایل موبایل
 *
 * نمونه‌ها:
 * IMEI، RAM، Storage، Warranty
 */
@Composable
fun MobileAttributeRow(
    label: String,
    value: String
) {
    Text(text = "$label: $value")
}
