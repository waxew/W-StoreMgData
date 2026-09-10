package com.wstore.engine.ui.profile.mobile_store_001.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * گزارشات اختصاصی پروفایل فروشگاه موبایل.
 *
 * داده‌ها از MobileReportsViewModel و در نهایت Repositoryهای واقعی خوانده می‌شوند.
 * سود واقعی نمایش داده نمی‌شود چون مدل Product هنوز بهای تمام‌شده ندارد؛
 * در عوض ارزش فروش موجودی به‌صورت دقیق از داده فعلی کالاها محاسبه می‌شود.
 */
@Composable
fun MobileReportsScreen(
    viewModel: MobileReportsViewModel,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "گزارشات فروشگاه موبایل")
            Button(onClick = onBack) {
                Text("بازگشت")
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.error?.let { message ->
            Text(text = message)
            Button(onClick = viewModel::refresh) {
                Text("تلاش دوباره")
            }
        }

        ReportCard(
            title = "فروش امروز",
            value = state.todaySales
        )

        ReportCard(
            title = "فروش ماه جاری",
            value = state.monthlySales
        )

        ReportCard(
            title = "کالاهای کم‌موجود",
            value = state.lowStockCount.toString()
        )

        ReportCard(
            title = "ارزش فروش موجودی",
            value = state.inventoryValue
        )

        Button(
            onClick = viewModel::refresh,
            modifier = Modifier.fillMaxWidth(),
            enabled = !state.isLoading
        ) {
            Text("به‌روزرسانی گزارش")
        }
    }
}

@Composable
private fun ReportCard(
    title: String,
    value: String
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = title)
            Text(text = value)
        }
    }
}
