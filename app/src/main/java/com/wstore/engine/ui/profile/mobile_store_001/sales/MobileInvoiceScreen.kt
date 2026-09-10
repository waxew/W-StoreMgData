package com.wstore.engine.ui.profile.mobile_store_001.sales

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * صفحه فاکتور فروش اختصاصی پروفایل فروشگاه موبایل.
 *
 * این لایه فقط UI Profile است.
 * منطق فروش، محاسبه مبلغ و ذخیره سازی از Sales/Invoice Core دریافت می‌شود.
 */
@Composable
fun MobileInvoiceScreen(
    customerName: String = "انتخاب مشتری",
    itemCount: Int = 0,
    totalAmount: String = "0"
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = "فاکتور فروش فروشگاه موبایل")

        Spacer(modifier = Modifier.padding(8.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "مشتری: $customerName")
                Text(text = "تعداد کالا: $itemCount")
                Text(text = "مبلغ نهایی: $totalAmount")
            }
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "ثبت فاکتور")
        }
    }
}
