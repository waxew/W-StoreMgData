package com.wstore.engine.ui.profile.mobile_store_001.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * داشبورد اختصاصی پروفایل فروشگاه موبایل
 *
 * این صفحه فقط لایه نمایش پروفایل است و منطق کسب و کار
 * از Core Engine و ViewModel دریافت می‌شود.
 */
@Composable
fun MobileDashboardScreen(
    modifier: Modifier = Modifier,
    storeName: String = "فروشگاه موبایل"
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = storeName,
            style = MaterialTheme.typography.headlineSmall
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MobileStatisticCard("فروش امروز")
            MobileStatisticCard("موجودی کالا")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MobileStatisticCard("مشتریان")
            MobileStatisticCard("سفارشات")
        }
    }
}

@Composable
private fun MobileStatisticCard(title: String) {
    Card(modifier = Modifier.weight(1f)) {
        Text(
            text = title,
            modifier = Modifier.padding(16.dp)
        )
    }
}
