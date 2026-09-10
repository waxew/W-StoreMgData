package com.wstore.engine.ui.profile.mobile_store_001.sales

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import com.wstore.engine.ui.sales.SalesViewModel

/**
 * صفحه فروش و فاکتور اختصاصی پروفایل فروشگاه موبایل.
 *
 * تمام مشتریان، کالاها، سبد فروش، موجودی، ثبت فروش و تاریخچه از SalesViewModel
 * و Repositoryهای واقعی پروژه دریافت می‌شوند. این صفحه فقط ظاهر Profile را تغییر می‌دهد.
 */
@Composable
fun MobileInvoiceScreen(
    viewModel: SalesViewModel,
    onSaleSelected: (Long) -> Unit
) {
    val customers by viewModel.customers.collectAsState()
    val products by viewModel.products.collectAsState()
    val cart by viewModel.cart.collectAsState()
    val selectedCustomerId by viewModel.selectedCustomerId.collectAsState()
    val note by viewModel.note.collectAsState()
    val sales by viewModel.sales.collectAsState()
    val error by viewModel.error.collectAsState()
    val success by viewModel.success.collectAsState()

    var selectedProductId by remember { mutableStateOf<Long?>(null) }
    var quantity by remember { mutableStateOf("1") }

    val productsById = products.associateBy { it.id }
    val cartTotal = cart.sumOf { line ->
        val product = productsById[line.productId]
        (product?.price ?: 0.0) * line.quantity
    }
    val selectedCustomer = customers.firstOrNull { it.id == selectedCustomerId }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "فروش و فاکتور",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "فروشگاه موبایل",
            style = MaterialTheme.typography.bodyMedium
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "مشتری",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = selectedCustomer?.let { "${it.name} — ${it.phone}" } ?: "مشتری آزاد"
                )

                TextButton(onClick = { viewModel.selectCustomer(null) }) {
                    Text(if (selectedCustomerId == null) "✓ مشتری آزاد" else "انتخاب مشتری آزاد")
                }

                customers.forEach { customer ->
                    TextButton(onClick = { viewModel.selectCustomer(customer.id) }) {
                        Text(
                            if (selectedCustomerId == customer.id) {
                                "✓ ${customer.name} — ${customer.phone}"
                            } else {
                                "${customer.name} — ${customer.phone}"
                            }
                        )
                    }
                }
            }
        }

        HorizontalDivider()
        Text(
            text = "انتخاب کالا",
            style = MaterialTheme.typography.titleMedium
        )

        if (products.isEmpty()) {
            Text("کالایی برای فروش ثبت نشده است.")
        } else {
            products.forEach { product ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        if (product.stock > 0) selectedProductId = product.id
                    }
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text("قیمت: ${product.price}")
                        Text("موجودی: ${product.stock}")
                        if (selectedProductId == product.id) {
                            Text("انتخاب شده")
                        }
                        if (product.stock <= 0) {
                            Text(
                                text = "ناموجود",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            }
        }

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("تعداد") },
            singleLine = true
        )

        Button(
            onClick = {
                selectedProductId?.let { productId ->
                    viewModel.addToCart(productId, quantity)
                    quantity = "1"
                }
            },
            enabled = selectedProductId != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("افزودن به سبد")
        }

        HorizontalDivider()
        Text(
            text = "اقلام فاکتور",
            style = MaterialTheme.typography.titleMedium
        )

        if (cart.isEmpty()) {
            Text("سبد فروش خالی است.")
        } else {
            cart.forEach { line ->
                val product = productsById[line.productId]
                if (product != null) {
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(product.name)
                                Text("${line.quantity} × ${product.price}")
                                Text("جمع: ${line.quantity * product.price}")
                            }
                            TextButton(onClick = { viewModel.removeFromCart(line.productId) }) {
                                Text("حذف")
                            }
                        }
                    }
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = "خلاصه فاکتور",
                    style = MaterialTheme.typography.titleMedium
                )
                Text("تعداد ردیف: ${cart.size}")
                Text("مبلغ نهایی: $cartTotal")
            }
        }

        OutlinedTextField(
            value = note,
            onValueChange = viewModel::setNote,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("توضیحات فروش") }
        )

        error?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }
        success?.let { message ->
            Text(message)
        }

        Button(
            onClick = viewModel::submitSale,
            enabled = cart.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ثبت نهایی فروش")
        }

        if (error != null || success != null) {
            TextButton(onClick = viewModel::clearMessages) {
                Text("بستن پیام")
            }
        }

        HorizontalDivider()
        Text(
            text = "فروش‌های اخیر",
            style = MaterialTheme.typography.titleMedium
        )

        if (sales.isEmpty()) {
            Text("هنوز فروشی ثبت نشده است.")
        } else {
            sales.take(20).forEach { sale ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text("فروش #${sale.id}")
                        Text("مشتری: ${sale.customerName}")
                        Text("مبلغ: ${sale.totalAmount}")
                        Button(onClick = { onSaleSelected(sale.id) }) {
                            Text("مشاهده جزئیات")
                        }
                    }
                }
            }
        }
    }
}
