package com.wstore.engine.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.wstore.engine.data.local.entity.ProductEntity

@Composable
fun AddProductForm(
    onSave: (ProductEntity) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    Column {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        TextField(
            value = code,
            onValueChange = { code = it },
            label = { Text("Code") }
        )

        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") }
        )

        Button(
            onClick = {
                onSave(
                    ProductEntity(
                        name = name,
                        code = code,
                        category = category,
                        price = 0.0,
                        stock = 0
                    )
                )
            }
        ) {
            Text("Save")
        }
    }
}
