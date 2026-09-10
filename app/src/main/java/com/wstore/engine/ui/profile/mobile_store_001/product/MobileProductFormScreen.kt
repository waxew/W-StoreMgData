package com.wstore.engine.ui.profile.mobile_store_001.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.model.Product
import com.wstore.engine.profile.ProfileAttributeDefinition
import com.wstore.engine.ui.product.AddProductForm
import com.wstore.engine.ui.product.ProductFormData

/**
 * نام فایل: MobileProductFormScreen.kt
 * پروفایل: mobile_store_001
 * وظیفه: پوسته گرافیکی اختصاصی فرم ثبت/ویرایش کالا برای فروشگاه موبایل.
 *
 * فیلدهای پایه توسط AddProductForm مشترک و فیلدهای IMEI/RAM/Storage/... توسط Schema پروفایل
 * رندر می‌شوند؛ بنابراین Product Core برای صنف موبایل تغییر نمی‌کند.
 */
@Composable
fun MobileProductFormScreen(
    initialProduct: Product?,
    dynamicAttributeDefinitions: List<ProfileAttributeDefinition>,
    initialAttributeValues: Map<String, String>,
    onSave: (ProductFormData) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = if (initialProduct == null) "افزودن کالای جدید" else "ویرایش کالا",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            AddProductForm(
                initialProduct = initialProduct,
                dynamicAttributeDefinitions = dynamicAttributeDefinitions,
                initialAttributeValues = initialAttributeValues,
                onSave = onSave,
                onCancel = onCancel
            )
        }
    }
}
