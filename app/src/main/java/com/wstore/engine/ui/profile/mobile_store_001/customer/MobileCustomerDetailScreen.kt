package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.local.entity.SaleEntity
import com.wstore.engine.ui.customer.CustomerDetailViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * صفحه جزئیات مشتری برای پروفایل فروشگاه موبایل.
 *
 * داده مشتری و سابقه خرید از CustomerDetailViewModel واقعی پروژه دریافت می‌شود؛
 * بنابراین این صفحه صرفاً ظاهر اختصاصی Profile را روی Backend موجود قرار می‌دهد.
 */
@Composable
fun MobileCustomerDetailScreen(
    viewModel: CustomerDetailViewModel,
    onBack: () -> Unit,
    onSaleSelected: (Long) -> Unit
) {
    val history by viewModel.history.collectAsState()
    val customer = history.customer

    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            TextButton(onClick = onBack) {
                Text("بازگشت")
            }
        }

        item {
            Text(
                text = "پروفایل مشتری",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        if (customer == null) {
            item {
                Text("اطلاعات مشتری در حال بارگذاری است یا مشتری پیدا نشد.")
            }
        } else {
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = customer.name,
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text("شماره تماس: ${customer.phone}")
                        if (customer.email.isNotBlank()) Text("ایمیل: ${customer.email}")
                        if (customer.address.isNotBlank()) Text("آدرس: ${customer.address}")
                        if (customer.notes.isNotBlank()) Text("یادداشت: ${customer.notes}")
                        Text("عضویت از: ${mobileCustomerDate(customer.createdAt)}")
                    }
                }
            }
        }

        item {
            HorizontalDivider()
            Text(
                text = "خلاصه خرید",
                style = MaterialTheme.typography.titleMedium
            )
            Text("تعداد خرید: ${history.summary.purchaseCount}")
            Text("مجموع خرید: ${history.summary.totalAmount}")
            Text(
                "آخرین خرید: ${history.summary.lastPurchaseAt?.let(::mobileCustomerDate) ?: "بدون خرید"}"
            )
        }

        item {
            HorizontalDivider()
            Text(
                text = "سوابق خرید",
                style = MaterialTheme.typography.titleMedium
            )
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
                MobileCustomerSaleCard(
                    sale = sale,
                    onOpen = { onSaleSelected(sale.id) }
                )
            }
        }
    }
}

@Composable
private fun MobileCustomerSaleCard(
    sale: SaleEntity,
    onOpen: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "فروش #${sale.id}",
                style = MaterialTheme.typography.titleMedium
            )
            Text("تاریخ: ${mobileCustomerDate(sale.createdAt)}")
            Text("مبلغ: ${sale.totalAmount}")
            if (sale.note.isNotBlank()) Text("توضیحات: ${sale.note}")
            Button(onClick = onOpen) {
                Text("جزئیات فروش")
            }
        }
    }
}

private fun mobileCustomerDate(value: Long): String =
    SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(Date(value))
