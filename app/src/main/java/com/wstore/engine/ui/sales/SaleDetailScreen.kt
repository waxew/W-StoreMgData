package com.wstore.engine.ui.sales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * صفحه جزئیات کامل یک فروش و اقلام Snapshot شده آن.
 */
@Composable
fun SaleDetailScreen(
    viewModel: SaleDetailViewModel,
    onBack: () -> Unit
) {
    val detail by viewModel.detail.collectAsState()
    val sale = detail.sale

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            TextButton(onClick = onBack) {
                Text("بازگشت")
            }
        }

        if (sale == null) {
            item {
                Text("فروش پیدا نشد یا در حال بارگذاری است.")
            }
        } else {
            item {
                Text("جزئیات فروش #${sale.id}")
            }

            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("مشتری: ${sale.customerName}")
                        Text("تاریخ: ${formatSaleTimestamp(sale.createdAt)}")
                        Text("مبلغ کل: ${sale.totalAmount}")
                        if (sale.note.isNotBlank()) {
                            Text("توضیحات: ${sale.note}")
                        }
                    }
                }
            }

            item {
                HorizontalDivider()
                Text("اقلام فروش")
            }

            if (detail.items.isEmpty()) {
                item {
                    Text("ردیفی برای این فروش ثبت نشده است.")
                }
            } else {
                items(
                    items = detail.items,
                    key = { item -> item.id }
                ) { item ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(item.productName)
                            if (item.productCode.isNotBlank()) {
                                Text("کد کالا: ${item.productCode}")
                            }
                            Text("تعداد: ${item.quantity}")
                            Text("قیمت واحد: ${item.unitPrice}")
                            Text("جمع ردیف: ${item.lineTotal}")
                        }
                    }
                }
            }
        }
    }
}

private fun formatSaleTimestamp(value: Long): String =
    SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(Date(value))
