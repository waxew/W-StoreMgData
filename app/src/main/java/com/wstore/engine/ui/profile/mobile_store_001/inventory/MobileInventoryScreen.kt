package com.wstore.engine.ui.profile.mobile_store_001.inventory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * صفحه موجودی اختصاصی فروشگاه موبایل.
 *
 * این صفحه در لایه Profile قرار دارد و داده را از Inventory Core دریافت می‌کند.
 */
@Composable
fun MobileInventoryScreen(
    productCount: String = "0",
    lowStockCount: String = "0"
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "موجودی فروشگاه موبایل")

        Card(modifier = Modifier.padding(top = 12.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "تعداد کالاها: $productCount")
                Text(text = "کمبود موجودی: $lowStockCount")
            }
        }
    }
}
