package com.wstore.engine.ui.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.wstore.engine.profile.ProfileRuntimeStore
import com.wstore.engine.ui.profile.mobile_store_001.product.MobileProductFormScreen
import com.wstore.engine.ui.profile.mobile_store_001.product.MobileProductListScreen

/**
 * نام فایل: ProductScreen.kt
 * ماژول: Product UI
 * وظیفه: مدیریت کالا و انتخاب Renderer مناسب بر اساس Business Profile فعال.
 *
 * ProductViewModel و Repository برای همه Profileها مشترک می‌مانند؛ فقط Presentation Layer بر اساس
 * Metadata پروفایل تغییر می‌کند. Generic UI نیز به عنوان fallback حفظ شده است.
 */
@Composable
fun ProductScreen(
    viewModel: ProductViewModel
) {
    val products by viewModel.products.collectAsState()
    val query by viewModel.query.collectAsState()
    val editingProduct by viewModel.editingProduct.collectAsState()
    val editingAttributeValues by viewModel.editingAttributeValues.collectAsState()
    val message by viewModel.message.collectAsState()
    val activeProfile = remember { ProfileRuntimeStore.current() }
    var pendingDelete by remember { mutableStateOf<Product?>(null) }

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    val onSave: (ProductFormData) -> Unit = { form ->
        val editing = editingProduct
        if (editing == null) {
            viewModel.onEvent(
                ProductEvent.AddProduct(
                    name = form.name,
                    code = form.code,
                    category = form.category,
                    price = form.price,
                    stock = form.stock,
                    attributes = form.attributes
                )
            )
        } else {
            viewModel.onEvent(
                ProductEvent.UpdateProduct(
                    id = editing.id,
                    name = form.name,
                    code = form.code,
                    category = form.category,
                    price = form.price,
                    attributes = form.attributes
                )
            )
        }
    }

    if (activeProfile.uiProfile.productCardVariant == "device_product_card") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp)
        ) {
            MobileProductFormScreen(
                initialProduct = editingProduct,
                dynamicAttributeDefinitions = viewModel.attributeDefinitions,
                initialAttributeValues = editingAttributeValues,
                onSave = onSave,
                onCancel = { viewModel.onEvent(ProductEvent.CancelEdit) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            message?.let { text ->
                ProductMessage(
                    message = text,
                    onDismiss = { viewModel.onEvent(ProductEvent.DismissMessage) },
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            MobileProductListScreen(
                products = products,
                query = query,
                onQueryChange = { viewModel.onEvent(ProductEvent.Search(it)) },
                onEdit = { viewModel.onEvent(ProductEvent.StartEdit(it)) },
                onDelete = { pendingDelete = it },
                modifier = Modifier.weight(1f)
            )
        }
    } else {
        GenericProductContent(
            profileName = activeProfile.name,
            products = products,
            query = query,
            editingProduct = editingProduct,
            editingAttributeValues = editingAttributeValues,
            attributeDefinitions = viewModel.attributeDefinitions,
            message = message,
            onQueryChange = { viewModel.onEvent(ProductEvent.Search(it)) },
            onSave = onSave,
            onCancelEdit = { viewModel.onEvent(ProductEvent.CancelEdit) },
            onDismissMessage = { viewModel.onEvent(ProductEvent.DismissMessage) },
            onEdit = { viewModel.onEvent(ProductEvent.StartEdit(it)) },
            onDelete = { pendingDelete = it }
        )
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
private fun GenericProductContent(
    profileName: String,
    products: List<Product>,
    query: String,
    editingProduct: Product?,
    editingAttributeValues: Map<String, String>,
    attributeDefinitions: List<com.wstore.engine.profile.ProfileAttributeDefinition>,
    message: String?,
    onQueryChange: (String) -> Unit,
    onSave: (ProductFormData) -> Unit,
    onCancelEdit: () -> Unit,
    onDismissMessage: () -> Unit,
    onEdit: (Product) -> Unit,
    onDelete: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("مدیریت کالا — $profileName")
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = query,
            onValueChange = onQueryChange,
            label = { Text("جستجو بر اساس نام، کد یا دسته‌بندی") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        AddProductForm(
            initialProduct = editingProduct,
            dynamicAttributeDefinitions = attributeDefinitions,
            initialAttributeValues = editingAttributeValues,
            onSave = onSave,
            onCancel = onCancelEdit
        )

        message?.let { text ->
            ProductMessage(
                message = text,
                onDismiss = onDismissMessage
            )
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
                    onEdit = { onEdit(product) },
                    onDelete = { onDelete(product) }
                )
            }
        }
    }
}

@Composable
private fun ProductMessage(
    message: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(message, modifier = Modifier.weight(1f))
        TextButton(onClick = onDismiss) {
            Text("بستن")
        }
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
