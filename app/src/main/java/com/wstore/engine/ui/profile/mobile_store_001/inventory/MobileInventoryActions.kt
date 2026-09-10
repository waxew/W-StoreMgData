package com.wstore.engine.ui.profile.mobile_store_001.inventory

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

/**
 * اکشن های موجودی پروفایل فروشگاه موبایل.
 * اتصال به Inventory ViewModel در مرحله Binding انجام می‌شود.
 */
@Composable
fun MobileInventoryActions(
    onStockIn: () -> Unit = {},
    onStockOut: () -> Unit = {}
) {
    Button(onClick = onStockIn) {
        Text("ورود کالا")
    }

    Button(onClick = onStockOut) {
        Text("خروج کالا")
    }
}
