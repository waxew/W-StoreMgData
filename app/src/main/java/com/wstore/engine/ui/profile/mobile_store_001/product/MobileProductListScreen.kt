package com.wstore.engine.ui.profile.mobile_store_001.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.model.Product
import com.wstore.engine.ui.profile.mobile_store_001.components.MobileProductCard

/**
 * نام فایل: MobileProductListScreen.kt
 * پروفایل: mobile_store_001
 * وظیفه: نمایش لیست واقعی کالاهای فروشگاه موبایل با جستجو و عملیات جزئیات/ویرایش/حذف.
 *
 * داده‌ها از ProductViewModel وارد این Screen می‌شوند و هیچ داده نمونه‌ای در UI نگهداری نمی‌شود.
 */
@Composable
fun MobileProductListScreen(
    products: List<Product>,
    query: String,
    onQueryChange: (String) -> Unit,
    onView: (Product) -> Unit,
    onEdit: (Product) -> Unit,
    onDelete: (Product) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "کالاهای فروشگاه موبایل",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text("جستجوی نام، کد یا دسته‌بندی") }
        )

        Text(
            text = "${products.size} کالا",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(2.dp))

        if (products.isEmpty()) {
            Text(
                text = if (query.isBlank()) {
                    "هنوز کالایی ثبت نشده است."
                } else {
                    "کالایی مطابق جستجو پیدا نشد."
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = products,
                key = { it.id }
            ) { product ->
                MobileProductCard(
                    product = product,
                    onView = { onView(product) },
                    onEdit = { onEdit(product) },
                    onDelete = { onDelete(product) }
                )
            }
        }
    }
}
