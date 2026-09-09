package com.wstore.engine.ui.customer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
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

/**
 * نام فایل: CustomerScreen.kt
 * ماژول: Customer
 * وظیفه: ثبت، ویرایش، حذف، جستجو، نمایش مشتریان و ورود به تاریخچه خرید.
 *
 * این صفحه فقط با CustomerViewModel کار می‌کند و مستقیماً به Room وابسته نیست.
 */
@Composable
fun CustomerScreen(
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
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("مدیریت مشتریان")

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
                    Text("لغو ویرایش")
                }
            }
        }

        error?.let { message ->
            Text(message)
        }

        Text("مشتریان: ${customers.size}")

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = customers,
                key = { customer -> customer.id }
            ) { customer ->
                CustomerCard(
                    customer = customer,
                    onHistory = { onCustomerSelected(customer.id) },
                    onEdit = {
                        editingCustomer = customer
                        name = customer.name
                        phone = customer.phone
                        email = customer.email
                        address = customer.address
                        notes = customer.notes
                        viewModel.clearError()
                    },
                    onDelete = {
                        viewModel.deleteCustomer(customer)
                        if (editingCustomer?.id == customer.id) {
                            clearForm()
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun CustomerCard(
    customer: CustomerEntity,
    onHistory: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(customer.name)
            Text(customer.phone)

            if (customer.email.isNotBlank()) {
                Text(customer.email)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextButton(onClick = onHistory) {
                    Text("خریدها")
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
