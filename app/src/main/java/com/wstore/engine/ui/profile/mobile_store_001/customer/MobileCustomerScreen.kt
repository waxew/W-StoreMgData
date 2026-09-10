package com.wstore.engine.ui.profile.mobile_store_001.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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
import com.wstore.engine.data.local.entity.CustomerEntity
import com.wstore.engine.ui.customer.CustomerViewModel

/**
 * صفحه مشتریان پروفایل فروشگاه موبایل.
 *
 * UI مطابق هویت mobile_store_001 است اما عملیات CRUD و جستجو
 * مستقیماً از CustomerViewModel و Repository اصلی پروژه استفاده می‌کنند.
 */
@Composable
fun MobileCustomerScreen(
    viewModel: CustomerViewModel,
    onCustomerSelected: (Long) -> Unit
) {
    val customers by viewModel.customers.collectAsState()
    val error by viewModel.error.collectAsState()

    var search by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var editingCustomer by remember { mutableStateOf<CustomerEntity?>(null) }

    fun clearForm() {
        name = ""
        phone = ""
        email = ""
        address = ""
        notes = ""
        editingCustomer = null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "مشتریان فروشگاه موبایل",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "ثبت مشتری، جستجو و دسترسی به سوابق خرید",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = search,
            onValueChange = {
                search = it
                viewModel.search(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("جستجو با نام یا شماره تماس") },
            singleLine = true
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = if (editingCustomer == null) "مشتری جدید" else "ویرایش مشتری",
                    style = MaterialTheme.typography.titleMedium
                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("نام مشتری") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("شماره تماس") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("ایمیل") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("آدرس") }
                )
                OutlinedTextField(
                    value = notes,
                    onValueChange = { notes = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("یادداشت") }
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = {
                            val current = editingCustomer
                            if (current == null) {
                                viewModel.addCustomer(name, phone, email, address, notes)
                            } else {
                                viewModel.updateCustomer(
                                    current,
                                    name,
                                    phone,
                                    email,
                                    address,
                                    notes
                                )
                            }

                            if (name.isNotBlank() && phone.isNotBlank()) {
                                clearForm()
                            }
                        }
                    ) {
                        Text(if (editingCustomer == null) "ثبت مشتری" else "ذخیره ویرایش")
                    }

                    if (editingCustomer != null) {
                        TextButton(onClick = { clearForm() }) {
                            Text("لغو")
                        }
                    }
                }
            }
        }

        error?.let { message ->
            Text(
                text = message,
                color = MaterialTheme.colorScheme.error
            )
        }

        Text(
            text = "مشتریان (${customers.size})",
            style = MaterialTheme.typography.titleMedium
        )

        MobileCustomerListSection(
            customers = customers,
            onHistory = { customer -> onCustomerSelected(customer.id) },
            onEdit = { customer ->
                editingCustomer = customer
                name = customer.name
                phone = customer.phone
                email = customer.email
                address = customer.address
                notes = customer.notes
                viewModel.clearError()
            },
            onDelete = { customer ->
                viewModel.deleteCustomer(customer)
                if (editingCustomer?.id == customer.id) {
                    clearForm()
                }
            },
            modifier = Modifier.weight(1f)
        )
    }
}
