package com.wstore.engine.ui.profile.mobile_store_001.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * صفحه گزارشات اختصاصی پروفایل فروشگاه موبایل.
 *
 * این لایه فقط مسئول نمایش UI پروفایل است.
 * منطق گزارش گیری در Report Core و Repository باقی می ماند.
 */
@Composable
fun MobileReportsScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "گزارشات فروشگاه موبایل")

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ReportCard("فروش امروز")
            ReportCard("موجودی")
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            ReportCard("سود و زیان")
            ReportCard("فروش ماهانه")
        }
    }
}

@Composable
private fun ReportCard(title: String) {
    Card {
        Text(text = title)
    }
}
