package com.wstore.engine.ui.sales

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * صفحه فروش واقعی: انتخاب مشتری، افزودن کالا به سبد، ثبت فروش و مشاهده تاریخچه.
 */
@Composable
fun SalesScreen(
    viewModel: SalesViewModel
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

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("مدیریت فروش")
        Spacer(Modifier.height(12.dp))

        Text("مشتری")
        Button(
            onClick = { viewModel.selectCustomer(null) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (selectedCustomerId == null) "✓ مشتری آزاد" else "مشتری آزاد")
        }
        customers.forEach { customer ->
            Button(
                onClick = { viewModel.selectCustomer(customer.id) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (selectedCustomerId == customer.id) {
                        "✓ ${customer.name} - ${customer.phone}"
                    } else {
                        "${customer.name} - ${customer.phone}"
                    }
                )
            }
        }

        Spacer(Modifier.height(16.dp))
        HorizontalDivider()
        Spacer(Modifier.height(16.dp))

        Text("انتخاب کالا")
        products.forEach { product ->
            Button(
                onClick = { selectedProductId = product.id },
                enabled = product.stock > 0,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    buildString {
                        if (selectedProductId == product.id) append("✓ ")
                        append(product.name)
                        append(" | موجودی: ")
                        append(product.stock)
                        append(" | قیمت: ")
                        append(product.price)
                    }
                )
            }
        }

        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("تعداد") },
            modifier = Modifier.fillMaxWidth()
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

        Spacer(Modifier.height(16.dp))
        Text("سبد فروش")
        if (cart.isEmpty()) {
            Text("سبد خالی است.")
        }
        cart.forEach { line ->
            val product = productsById[line.productId]
            if (product != null) {
                Text("${product.name} × ${line.quantity} = ${product.price * line.quantity}")
                Button(onClick = { viewModel.removeFromCart(line.productId) }) {
                    Text("حذف از سبد")
                }
            }
        }
        Text("جمع کل: $cartTotal")

        OutlinedTextField(
            value = note,
            onValueChange = viewModel::setNote,
            label = { Text("توضیحات فروش") },
            modifier = Modifier.fillMaxWidth()
        )

        if (error != null) {
            Text(error.orEmpty())
        }
        if (success != null) {
            Text(success.orEmpty())
        }

        Button(
            onClick = viewModel::submitSale,
            enabled = cart.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ثبت نهایی فروش")
        }

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(12.dp))
        Text("تاریخچه فروش")
        sales.forEach { sale ->
            Text(
                "#${sale.id} | ${sale.customerName} | مبلغ: ${sale.totalAmount} | ${sale.note}"
            )
        }
    }
}
