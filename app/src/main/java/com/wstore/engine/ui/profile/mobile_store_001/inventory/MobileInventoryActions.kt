package com.wstore.engine.ui.profile.mobile_store_001.inventory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * اکشن‌های موجودی پروفایل فروشگاه موبایل.
 * عملیات واقعی از InventoryViewModel به این کامپوننت تزریق می‌شوند.
 */
@Composable
fun MobileInventoryActions(
    onStockIn: () -> Unit,
    onStockOut: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(
            onClick = onStockIn,
            enabled = enabled,
            modifier = Modifier.weight(1f)
        ) {
            Text("ورود کالا")
        }

        Button(
            onClick = onStockOut,
            enabled = enabled,
            modifier = Modifier.weight(1f)
        ) {
            Text("خروج کالا")
        }
    }
}
