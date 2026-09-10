package com.wstore.engine.ui.profile.mobile_store_001.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * کامپوننت کارت محصول مخصوص پروفایل فروشگاه موبایل
 * نمایش اطلاعات پایه کالا بدون وابستگی به Core Product
 */
@Composable
fun MobileProductCard(
    title: String,
    brand: String,
    price: String,
    stock: String
) {
    Card {
        Column {
            Text(text = title)
            Text(text = brand)
            Text(text = price)
            Text(text = stock)
        }
    }
}
