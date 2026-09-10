package com.wstore.engine.ui.profile.mobile_store_001.product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.model.Product
import com.wstore.engine.profile.ProfileAttributeDefinition
import com.wstore.engine.ui.profile.mobile_store_001.components.MobileAttributeRow
import java.util.Locale

/**
 * صفحه جزئیات محصول پروفایل موبایل.
 *
 * اطلاعات پایه از Product مشترک و مشخصات صنفی از Dynamic Attribute Storage خوانده می‌شوند.
 * هیچ مقدار نمونه یا IMEI ساختگی در این صفحه نگهداری نمی‌شود.
 */
@Composable
fun MobileProductDetailScreen(
    product: Product,
    attributeDefinitions: List<ProfileAttributeDefinition>,
    attributeValues: Map<String, String>,
    onEdit: () -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "جزئیات کالا",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                TextButton(onClick = onClose) {
                    Text("بستن")
                }
            }

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text("کد: ${product.code.ifBlank { "-" }}")
            Text("دسته‌بندی: ${product.category.ifBlank { "-" }}")
            Text("قیمت: ${formatAmount(product.price)}")
            Text("موجودی فعلی: ${product.stock}")

            if (attributeDefinitions.isNotEmpty()) {
                Text(
                    text = "مشخصات اختصاصی",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                attributeDefinitions.forEach { definition ->
                    MobileAttributeRow(
                        label = definition.label,
                        value = attributeValues[definition.key].orEmpty().ifBlank { "ثبت نشده" }
                    )
                }
            }

            Button(
                onClick = onEdit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("ویرایش کالا")
            }
        }
    }
}

private fun formatAmount(value: Double): String =
    String.format(Locale.getDefault(), "%,.0f", value)
