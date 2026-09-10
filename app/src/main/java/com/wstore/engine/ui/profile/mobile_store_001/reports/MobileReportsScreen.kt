package com.wstore.engine.ui.profile.mobile_store_001.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * گزارشات اختصاصی پروفایل فروشگاه موبایل.
 *
 * این صفحه فقط Profile UI است.
 * منطق محاسبه گزارش در Report Core و Repository باقی می‌ماند.
 */
@Composable
fun MobileReportsScreen(
    modifier: Modifier = Modifier,
    todaySales: String = "0",
    monthlySales: String = "0",
    lowStockCount: String = "0",
    profit: String = "0"
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "گزارشات فروشگاه موبایل")

        ReportCard(
            title = "فروش امروز",
            value = todaySales
        )

        ReportCard(
            title = "فروش ماهانه",
            value = monthlySales
        )

        ReportCard(
            title = "کالاهای کم موجود",
            value = lowStockCount
        )

        ReportCard(
            title = "سود تقریبی",
            value = profit
        )
    }
}

@Composable
private fun ReportCard(
    title: String,
    value: String
) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Text(text = value)
        }
    }
}
