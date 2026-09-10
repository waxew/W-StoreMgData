package com.wstore.engine.ui.profile.mobile_store_001.inventory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * صفحه موجودی اختصاصی فروشگاه موبایل.
 *
 * این صفحه فقط مسئول نمایش UI پروفایل است.
 * داده واقعی از Inventory Core و ViewModel تزریق می‌شود.
 */
@Composable
fun MobileInventoryScreen(
    productCount: String = "0",
    lowStockCount: String = "0",
    incomingCount: String = "0",
    outgoingCount: String = "0"
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "موجودی فروشگاه موبایل")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MobileInventoryCard("کل کالا", productCount)
            MobileInventoryCard("کمبود", lowStockCount)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MobileInventoryCard("ورودی", incomingCount)
            MobileInventoryCard("خروجی", outgoingCount)
        }
    }
}

@Composable
private fun MobileInventoryCard(
    title: String,
    value: String
) {
    Card(modifier = Modifier.weight(1f)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Text(text = value)
        }
    }
}
