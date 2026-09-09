package com.wstore.engine.ui.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.local.entity.SaleEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * صفحه جزئیات مشتری و تاریخچه خریدهای ثبت‌شده او.
 */
@Composable
fun CustomerDetailScreen(
    viewModel: CustomerDetailViewModel,
    onBack: () -> Unit,
    onSaleSelected: (Long) -> Unit
) {
    val history by viewModel.history.collectAsState()
    val customer = history.customer

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            TextButton(onClick = onBack) {
                Text("بازگشت")
            }
        }

        item {
            Text("جزئیات مشتری")
        }

        if (customer == null) {
            item {
                Text("مشتری پیدا نشد یا در حال بارگذاری است.")
            }
        } else {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(customer.name)
                        Text("تماس: ${customer.phone}")
                        if (customer.email.isNotBlank()) Text("ایمیل: ${customer.email}")
                        if (customer.address.isNotBlank()) Text("آدرس: ${customer.address}")
                        if (customer.notes.isNotBlank()) Text("یادداشت: ${customer.notes}")
                        Text("تاریخ ایجاد: ${formatTimestamp(customer.createdAt)}")
                    }
                }
            }
        }

        item {
            HorizontalDivider()
            Text("خلاصه خرید")
            Text("تعداد خریدها: ${history.summary.purchaseCount}")
            Text("مجموع خرید: ${history.summary.totalAmount}")
            Text(
                "آخرین خرید: ${history.summary.lastPurchaseAt?.let(::formatTimestamp) ?: "بدون خرید"}"
            )
        }

        item {
            HorizontalDivider()
            Text("تاریخچه خرید")
        }

        if (history.sales.isEmpty()) {
            item {
                Text("برای این مشتری هنوز فروشی ثبت نشده است.")
            }
        } else {
            items(
                items = history.sales,
                key = { sale -> sale.id }
            ) { sale ->
                CustomerSaleCard(
                    sale = sale,
                    onOpen = { onSaleSelected(sale.id) }
                )
            }
        }
    }
}

@Composable
private fun CustomerSaleCard(
    sale: SaleEntity,
    onOpen: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text("فروش #${sale.id}")
            Text("تاریخ: ${formatTimestamp(sale.createdAt)}")
            Text("مبلغ: ${sale.totalAmount}")
            if (sale.note.isNotBlank()) Text("توضیحات: ${sale.note}")
            Button(onClick = onOpen) {
                Text("مشاهده جزئیات فروش")
            }
        }
    }
}

private fun formatTimestamp(value: Long): String =
    SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(Date(value))
