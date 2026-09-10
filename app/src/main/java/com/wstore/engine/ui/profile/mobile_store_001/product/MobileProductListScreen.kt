package com.wstore.engine.ui.profile.mobile_store_001.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.wstore.engine.ui.profile.mobile_store_001.components.MobileProductCard

/**
 * صفحه لیست کالاهای پروفایل فروشگاه موبایل
 * اتصال ViewModel و Repository در مرحله بعد تکمیل می‌شود.
 */
@Composable
fun MobileProductListScreen() {
    Column {
        Text(text = "کالاهای فروشگاه موبایل")

        MobileProductCard(
            title = "نمونه محصول",
            brand = "Brand",
            price = "0",
            stock = "موجودی: 0"
        )
    }
}
