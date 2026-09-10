package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.data.local.entity.CustomerEntity

/**
 * لیست مشتریان پروفایل فروشگاه موبایل.
 * داده واقعی CustomerEntity را نمایش می‌دهد و رویدادها را به لایه بالاتر برمی‌گرداند.
 */
@Composable
fun MobileCustomerListSection(
    customers: List<CustomerEntity>,
    onHistory: (CustomerEntity) -> Unit,
    onEdit: (CustomerEntity) -> Unit,
    onDelete: (CustomerEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    if (customers.isEmpty()) {
        Text("مشتری‌ای برای نمایش وجود ندارد.")
        return
    }

    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = customers,
            key = { customer -> customer.id }
        ) { customer ->
            MobileCustomerCard(
                customer = customer,
                onHistory = { onHistory(customer) },
                onEdit = { onEdit(customer) },
                onDelete = { onDelete(customer) }
            )
        }
    }
}
