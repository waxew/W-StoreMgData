package com.wstore.engine.ui.profile.mobile_store_001.invoice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.ui.invoice.InvoiceViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * مدیریت فاکتور اختصاصی پروفایل فروشگاه موبایل.
 *
 * این Renderer از همان InvoiceViewModel و InvoiceRepository اصلی استفاده می‌کند؛
 * بنابراین صدور فاکتور و Snapshotهای تاریخی در Core باقی می‌مانند و فقط ظاهر Profile تغییر می‌کند.
 */
@Composable
fun MobileInvoiceManagementScreen(
    viewModel: InvoiceViewModel
) {
    val invoices by viewModel.invoices.collectAsState()
    val salesWithoutInvoice by viewModel.salesWithoutInvoice.collectAsState()
    val selectedInvoiceId by viewModel.selectedInvoiceId.collectAsState()
    val selectedItems by viewModel.selectedItems.collectAsState()
    val error by viewModel.error.collectAsState()

    val selectedInvoice = invoices.firstOrNull { invoice -> invoice.id == selectedInvoiceId }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "فاکتورهای فروشگاه موبایل",
                style = MaterialTheme.typography.headlineSmall
            )
        }

        error?.let { message ->
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(message)
                        Button(onClick = viewModel::clearError) {
                            Text("بستن پیام")
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "فروش‌های آماده صدور فاکتور",
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (salesWithoutInvoice.isEmpty()) {
            item { Text("همه فروش‌های ثبت‌شده دارای فاکتور هستند.") }
        } else {
            items(
                items = salesWithoutInvoice,
                key = { sale -> "mobile-sale-${sale.id}" }
            ) { sale ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("فروش #${sale.id}", style = MaterialTheme.typography.titleMedium)
                        Text("مشتری: ${sale.customerName}")
                        Text("مبلغ: ${mobileFormatMoney(sale.totalAmount)}")
                        Text("تاریخ: ${mobileFormatDate(sale.createdAt)}")
                        Button(
                            onClick = { viewModel.createInvoice(sale.id) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("صدور فاکتور")
                        }
                    }
                }
            }
        }

        item {
            HorizontalDivider()
            Text(
                text = "فاکتورهای صادرشده",
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (invoices.isEmpty()) {
            item { Text("هنوز فاکتوری صادر نشده است.") }
        } else {
            items(
                items = invoices,
                key = { invoice -> "mobile-invoice-${invoice.id}" }
            ) { invoice ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(invoice.invoiceNumber, style = MaterialTheme.typography.titleMedium)
                        Text("مشتری: ${invoice.customerNameSnapshot}")
                        Text("مبلغ نهایی: ${mobileFormatMoney(invoice.totalAmount)}")
                        Text("صدور: ${mobileFormatDate(invoice.createdAt)}")
                        Button(
                            onClick = { viewModel.selectInvoice(invoice.id) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("جزئیات فاکتور")
                        }
                    }
                }
            }
        }

        selectedInvoice?.let { invoice ->
            item {
                HorizontalDivider()
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("جزئیات ${invoice.invoiceNumber}", style = MaterialTheme.typography.titleLarge)
                        Text("فروش مرجع: #${invoice.saleId}")
                        Text("مشتری: ${invoice.customerNameSnapshot}")
                        Text("تاریخ فروش: ${mobileFormatDate(invoice.saleCreatedAt)}")
                        Text("تاریخ صدور: ${mobileFormatDate(invoice.createdAt)}")
                        if (invoice.noteSnapshot.isNotBlank()) {
                            Text("یادداشت: ${invoice.noteSnapshot}")
                        }
                        Text("جمع کل: ${mobileFormatMoney(invoice.totalAmount)}")
                        Button(onClick = viewModel::clearSelection) {
                            Text("بستن جزئیات")
                        }
                    }
                }
            }

            items(
                items = selectedItems,
                key = { item -> "mobile-invoice-item-${item.id}" }
            ) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(item.productNameSnapshot, style = MaterialTheme.typography.titleMedium)
                        Text("کد کالا: ${item.productCodeSnapshot}")
                        Text("تعداد: ${item.quantity}")
                        Text("قیمت واحد: ${mobileFormatMoney(item.unitPrice)}")
                        Text("جمع ردیف: ${mobileFormatMoney(item.lineTotal)}")
                    }
                }
            }
        }
    }
}

private fun mobileFormatMoney(value: Double): String =
    String.format(Locale.getDefault(), "%,.0f", value)

private fun mobileFormatDate(timestamp: Long): String =
    SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(Date(timestamp))
