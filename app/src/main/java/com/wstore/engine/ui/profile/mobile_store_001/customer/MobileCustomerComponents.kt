package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.local.entity.CustomerEntity

/**
 * کامپوننت‌های اختصاصی پروفایل فروشگاه موبایل.
 *
 * این لایه فقط ظاهر Profile UI را نگهداری می‌کند و تمام عملیات
 * از CustomerViewModel و CustomerRepository فعلی عبور می‌کنند.
 */
@Composable
fun MobileCustomerCard(
    customer: CustomerEntity,
    onHistory: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = customer.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = customer.phone,
                style = MaterialTheme.typography.bodyMedium
            )

            if (customer.email.isNotBlank()) {
                Text(
                    text = customer.email,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = onHistory) {
                    Text("سوابق خرید")
                }
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
