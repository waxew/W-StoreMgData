package com.wstore.engine.ui.profile.mobile_store_001.inventory

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.repository.InventoryRepository
import com.wstore.engine.ui.inventory.InventoryViewModel

/**
 * صفحه موجودی اختصاصی فروشگاه موبایل.
 *
 * UI این صفحه از تصویر مرجع mobile_store_001 پیروی می‌کند و تمام داده‌ها و عملیات
 * ورود/خروج کالا از InventoryViewModel و Repository واقعی پروژه دریافت می‌شوند.
 */
@Composable
fun MobileInventoryScreen(
    viewModel: InventoryViewModel
) {
    val products by viewModel.products.collectAsState()
    val transactions by viewModel.transactions.collectAsState()
    val error by viewModel.error.collectAsState()

    var selectedProductId by remember { mutableStateOf<Long?>(null) }
    var quantity by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    val selectedProduct = products.firstOrNull { it.id == selectedProductId }
    val lowStockCount = products.count { it.stock <= 5 }
    val incomingCount = transactions.count { it.type == InventoryRepository.TYPE_IN }
    val outgoingCount = transactions.count { it.type == InventoryRepository.TYPE_OUT }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "موجودی فروشگاه موبایل",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "کنترل موجودی، ورود و خروج کالا",
            style = MaterialTheme.typography.bodyMedium
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MobileInventoryMetricCard(
                title = "کل کالا",
                value = products.size.toString(),
                modifier = Modifier.weight(1f)
            )
            MobileInventoryMetricCard(
                title = "کم‌موجودی",
                value = lowStockCount.toString(),
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MobileInventoryMetricCard(
                title = "ورودی‌ها",
                value = incomingCount.toString(),
                modifier = Modifier.weight(1f)
            )
            MobileInventoryMetricCard(
                title = "خروجی‌ها",
                value = outgoingCount.toString(),
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider()
        Text(
            text = "انتخاب کالا",
            style = MaterialTheme.typography.titleMedium
        )

        if (products.isEmpty()) {
            Text("برای مدیریت موجودی ابتدا یک کالا در بخش کالاها ثبت کنید.")
        } else {
            products.forEach { product ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { selectedProductId = product.id }
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text("کد کالا: ${product.code.ifBlank { "-" }}")
                        Text("موجودی: ${product.stock}")
                        if (product.stock <= 5) {
                            Text(
                                text = "نیاز به تأمین",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        if (selectedProductId == product.id) {
                            Text("انتخاب شده")
                        }
                    }
                }
            }
        }

        selectedProduct?.let { product ->
            HorizontalDivider()
            Text(
                text = "گردش موجودی — ${product.name}",
                style = MaterialTheme.typography.titleMedium
            )

            OutlinedTextField(
                value = quantity,
                onValueChange = { quantity = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("تعداد") },
                singleLine = true
            )

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("توضیحات") }
            )

            MobileInventoryActions(
                enabled = quantity.isNotBlank(),
                onStockIn = {
                    viewModel.stockIn(product.id, quantity, note)
                    quantity = ""
                    note = ""
                },
                onStockOut = {
                    viewModel.stockOut(product.id, quantity, note)
                    quantity = ""
                    note = ""
                }
            )
        }

        error?.let { message ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = message,
                        color = MaterialTheme.colorScheme.error
                    )
                    TextButton(onClick = viewModel::clearError) {
                        Text("بستن پیام")
                    }
                }
            }
        }

        HorizontalDivider()
        Text(
            text = "آخرین گردش‌های موجودی",
            style = MaterialTheme.typography.titleMedium
        )

        if (transactions.isEmpty()) {
            Text("هنوز گردش موجودی ثبت نشده است.")
        } else {
            transactions.take(20).forEach { transaction ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(transaction.productName)
                        Text("کد: ${transaction.productCode.ifBlank { "-" }}")
                        Text(
                            if (transaction.type == InventoryRepository.TYPE_IN) {
                                "ورود: +${transaction.quantityDelta}"
                            } else {
                                "خروج: ${transaction.quantityDelta}"
                            }
                        )
                        Text("موجودی: ${transaction.stockBefore} ← ${transaction.stockAfter}")
                        if (transaction.note.isNotBlank()) {
                            Text("توضیحات: ${transaction.note}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MobileInventoryMetricCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = value,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
