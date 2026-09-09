package com.wstore.engine.ui.reports

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * خروجی نمایشی گزارش‌های فروش و موجودی.
 */
@Composable
fun ReportsScreen(
    viewModel: ReportsViewModel,
    onBack: () -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val report = state.snapshot

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("گزارش‌های مدیریتی")
            Button(onClick = onBack) {
                Text("بازگشت")
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.error?.let { message ->
            Text(message)
        }

        Text("گزارش فروش")
        ReportMetricCard("تعداد فروش", report.salesCount.toString())
        ReportMetricCard("مجموع فروش", formatAmount(report.totalRevenue))
        ReportMetricCard("میانگین هر فروش", formatAmount(report.averageSale))

        Spacer(Modifier.height(4.dp))
        HorizontalDivider()
        Text("گزارش موجودی")
        ReportMetricCard("تعداد کالا", report.productCount.toString())
        ReportMetricCard("تعداد واحد موجود", report.totalStockUnits.toString())
        ReportMetricCard("ارزش فروش موجودی", formatAmount(report.inventoryRetailValue))
        ReportMetricCard("کم‌موجودی (۱ تا ۵)", report.lowStockCount.toString())
        ReportMetricCard("ناموجود", report.outOfStockCount.toString())

        Spacer(Modifier.height(4.dp))
        HorizontalDivider()
        Text("پرفروش‌ترین کالاها")

        if (!state.isLoading && report.topSellingProducts.isEmpty()) {
            Text("برای محاسبه پرفروش‌ها هنوز داده فروش کافی وجود ندارد.")
        }

        report.topSellingProducts.forEachIndexed { index, product ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("${index + 1}. ${product.productName}")
                    Text("تعداد فروخته‌شده: ${product.quantitySold}")
                    Text("مبلغ فروش: ${formatAmount(product.revenue)}")
                }
            }
        }

        Spacer(Modifier.height(4.dp))
        HorizontalDivider()
        Text("آخرین گردش‌های موجودی")

        if (!state.isLoading && report.recentInventoryMovements.isEmpty()) {
            Text("هنوز گردش موجودی ثبت نشده است.")
        }

        report.recentInventoryMovements.forEach { movement ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("${movement.productName} | ${movement.productCode}")
                    Text(
                        "نوع: ${movementTypeLabel(movement.type)} | تغییر: ${signedQuantity(movement.quantityDelta)}"
                    )
                    Text("موجودی بعد از عملیات: ${movement.stockAfter}")
                }
            }
        }
    }
}

@Composable
private fun ReportMetricCard(
    title: String,
    value: String
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title)
            Text(value)
        }
    }
}

private fun formatAmount(value: Double): String = String.format("%,.0f", value)

private fun signedQuantity(value: Int): String = if (value > 0) "+$value" else value.toString()

private fun movementTypeLabel(type: String): String = when (type) {
    "IN" -> "ورود"
    "OUT" -> "خروج"
    "SALE" -> "فروش"
    else -> type
}
