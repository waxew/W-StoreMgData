package com.wstore.engine.ui.inventory

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
import com.wstore.engine.data.repository.InventoryRepository
import com.wstore.engine.profile.ProfileRuntimeStore
import com.wstore.engine.profile.ProfileUiVariants
import com.wstore.engine.ui.profile.mobile_store_001.inventory.MobileInventoryScreen

/**
 * نام فایل: InventoryScreen.kt
 * ماژول: Inventory
 * وظیفه: نمایش موجودی فعلی، ثبت ورود/خروج و تاریخچه گردش کالا.
 *
 * Profileهای دارای UI اختصاصی از همان InventoryViewModel استفاده می‌کنند؛
 * Renderer از metadata پروفایل انتخاب می‌شود و منطق موجودی دو نسخه نمی‌شود.
 */
@Composable
fun InventoryScreen(
    viewModel: InventoryViewModel
) {
    if (ProfileRuntimeStore.currentOrNull()?.uiProfile?.navigationVariant == ProfileUiVariants.MOBILE_NAVIGATION) {
        MobileInventoryScreen(viewModel = viewModel)
        return
    }

    val products by viewModel.products.collectAsState()
    val transactions by viewModel.transactions.collectAsState()
    val error by viewModel.error.collectAsState()

    var selectedProductId by remember { mutableStateOf<Long?>(null) }
    var quantity by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    val selectedProduct = products.firstOrNull { it.id == selectedProductId }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("مدیریت موجودی")

        if (products.isEmpty()) {
            Text("برای ثبت گردش موجودی ابتدا حداقل یک کالا در ماژول Product ثبت کنید.")
        } else {
            Text("انتخاب کالا")

            products.forEach { product ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { selectedProductId = product.id }
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(product.name)
                        Text("کد: ${product.code}")
                        Text("موجودی فعلی: ${product.stock}")
                        if (selectedProductId == product.id) {
                            Text("انتخاب شده")
                        }
                    }
                }
            }
        }

        selectedProduct?.let { product ->
            HorizontalDivider()
            Text("گردش برای: ${product.name}")

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.stockIn(product.id, quantity, note)
                        quantity = ""
                        note = ""
                    }
                ) {
                    Text("ورود موجودی")
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        viewModel.stockOut(product.id, quantity, note)
                    }
                ) {
                    Text("خروج موجودی")
                }
            }
        }

        error?.let { message ->
            Text(message)
            Button(onClick = viewModel::clearError) {
                Text("بستن پیام")
            }
        }

        HorizontalDivider()
        Text("تاریخچه گردش موجودی")

        if (transactions.isEmpty()) {
            Text("هنوز گردش موجودی ثبت نشده است.")
        } else {
            transactions.forEach { transaction ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(transaction.productName)
                        Text("کد: ${transaction.productCode}")
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
