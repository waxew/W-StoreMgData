package com.wstore.engine.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.model.Product

/**
 * صفحه مدیریت کالا.
 * جستجو، ثبت، ویرایش و حذف کنترل‌شده از این صفحه انجام می‌شوند.
 */
@Composable
fun ProductScreen(
    viewModel: ProductViewModel
) {
    val products by viewModel.products.collectAsState()
    val query by viewModel.query.collectAsState()
    val editingProduct by viewModel.editingProduct.collectAsState()
    val message by viewModel.message.collectAsState()
    var pendingDelete by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("مدیریت کالا")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = {
                viewModel.onEvent(ProductEvent.Search(it))
            },
            label = { Text("جستجو بر اساس نام، کد یا دسته‌بندی") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        AddProductForm(
            initialProduct = editingProduct,
            onSave = { form ->
                val editing = editingProduct
                if (editing == null) {
                    viewModel.onEvent(
                        ProductEvent.AddProduct(
                            name = form.name,
                            code = form.code,
                            category = form.category,
                            price = form.price,
                            stock = form.stock
                        )
                    )
                } else {
                    viewModel.onEvent(
                        ProductEvent.UpdateProduct(
                            id = editing.id,
                            name = form.name,
                            code = form.code,
                            category = form.category,
                            price = form.price
                        )
                    )
                }
            },
            onCancel = {
                viewModel.onEvent(ProductEvent.CancelEdit)
            }
        )

        message?.let { text ->
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text, modifier = Modifier.weight(1f))
                TextButton(
                    onClick = { viewModel.onEvent(ProductEvent.DismissMessage) }
                ) {
                    Text("بستن")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("کالاها (${products.size})")
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = products,
                key = { it.id }
            ) { product ->
                ProductRow(
                    product = product,
                    onEdit = {
                        viewModel.onEvent(ProductEvent.StartEdit(product))
                    },
                    onDelete = {
                        pendingDelete = product
                    }
                )
            }
        }
    }

    pendingDelete?.let { product ->
        AlertDialog(
            onDismissRequest = { pendingDelete = null },
            title = { Text("حذف کالا") },
            text = {
                Text(
                    "آیا از حذف «${product.name}» مطمئن هستید؟ " +
                        "اگر این کالا سابقه فروش یا فاکتور داشته باشد، حذف برای حفظ تاریخچه انجام نمی‌شود."
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.onEvent(ProductEvent.DeleteProduct(product))
                        pendingDelete = null
                    }
                ) {
                    Text("حذف")
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingDelete = null }) {
                    Text("انصراف")
                }
            }
        )
    }
}

@Composable
private fun ProductRow(
    product: Product,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(product.name)
            Text("کد: ${product.code.ifBlank { "-" }}")
            Text("دسته‌بندی: ${product.category.ifBlank { "-" }}")
            Text("قیمت: ${product.price}")
            Text("موجودی: ${product.stock}")

            Row {
                TextButton(onClick = onEdit) {
                    Text("ویرایش")
                }
                TextButton(onClick = onDelete) {
                    Text("حذف")
                }
            }
        }
    }
}
