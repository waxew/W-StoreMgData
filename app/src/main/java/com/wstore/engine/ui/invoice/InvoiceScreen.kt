package com.wstore.engine.ui.invoice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * صفحه مدیریت فاکتورها.
 * فروش‌های بدون فاکتور را نمایش می‌دهد، فاکتور immutable می‌سازد و جزئیات اقلام را نشان می‌دهد.
 */
@Composable
fun InvoiceScreen(
    viewModel: InvoiceViewModel
) {
    val invoices by viewModel.invoices.collectAsState()
    val salesWithoutInvoice by viewModel.salesWithoutInvoice.collectAsState()
    val selectedInvoiceId by viewModel.selectedInvoiceId.collectAsState()
    val selectedItems by viewModel.selectedItems.collectAsState()
    val error by viewModel.error.collectAsState()

    val selectedInvoice = invoices.firstOrNull { it.id == selectedInvoiceId }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "فاکتورها",
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
                            Text("بستن")
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
            item {
                Text("فروش بدون فاکتور وجود ندارد.")
            }
        } else {
            items(
                items = salesWithoutInvoice,
                key = { "sale-${it.id}" }
            ) { sale ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("فروش #${sale.id} - ${sale.customerName}")
                        Text("مبلغ: ${formatMoney(sale.totalAmount)}")
                        Text("تاریخ فروش: ${formatDate(sale.createdAt)}")
                        Button(onClick = { viewModel.createInvoice(sale.id) }) {
                            Text("صدور فاکتور")
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "فاکتورهای صادرشده",
                style = MaterialTheme.typography.titleMedium
            )
        }

        if (invoices.isEmpty()) {
            item {
                Text("هنوز فاکتوری صادر نشده است.")
            }
        } else {
            items(
                items = invoices,
                key = { "invoice-${it.id}" }
            ) { invoice ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(invoice.invoiceNumber, style = MaterialTheme.typography.titleMedium)
                        Text("مشتری: ${invoice.customerNameSnapshot}")
                        Text("مبلغ: ${formatMoney(invoice.totalAmount)}")
                        Text("تاریخ صدور: ${formatDate(invoice.createdAt)}")
                        Button(onClick = { viewModel.selectInvoice(invoice.id) }) {
                            Text("مشاهده جزئیات")
                        }
                    }
                }
            }
        }

        selectedInvoice?.let { invoice ->
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("جزئیات ${invoice.invoiceNumber}", style = MaterialTheme.typography.titleLarge)
                        Text("فروش مرجع: #${invoice.saleId}")
                        Text("مشتری: ${invoice.customerNameSnapshot}")
                        Text("تاریخ فروش: ${formatDate(invoice.saleCreatedAt)}")
                        Text("تاریخ صدور: ${formatDate(invoice.createdAt)}")
                        if (invoice.noteSnapshot.isNotBlank()) {
                            Text("یادداشت: ${invoice.noteSnapshot}")
                        }
                        Text("جمع کل: ${formatMoney(invoice.totalAmount)}")
                        Button(onClick = viewModel::clearSelection) {
                            Text("بستن جزئیات")
                        }
                    }
                }
            }

            items(
                items = selectedItems,
                key = { "invoice-item-${it.id}" }
            ) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(item.productNameSnapshot, style = MaterialTheme.typography.titleMedium)
                        Text("کد: ${item.productCodeSnapshot}")
                        Text("تعداد: ${item.quantity}")
                        Text("قیمت واحد: ${formatMoney(item.unitPrice)}")
                        Text("جمع ردیف: ${formatMoney(item.lineTotal)}")
                    }
                }
            }
        }
    }
}

private fun formatMoney(value: Double): String = String.format(Locale.getDefault(), "%,.0f", value)

private fun formatDate(timestamp: Long): String =
    SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.getDefault()).format(Date(timestamp))
