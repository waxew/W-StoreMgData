package com.wstore.engine.ui.profile.mobile_store_001.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wstore.engine.ui.profile.mobile_store_001.components.MobileAttributeRow

/**
 * جزئیات محصول در پروفایل موبایل
 * فیلدهای اختصاصی از Dynamic Schema تغذیه خواهند شد.
 */
@Composable
fun MobileProductDetailScreen() {
    Column {
        Text(text = "جزئیات محصول موبایل")

        MobileAttributeRow(
            label = "IMEI",
            value = ""
        )
    }
}
