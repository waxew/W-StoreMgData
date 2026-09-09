package com.wstore.engine.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ProductScreen(
    viewModel: ProductViewModel
) {
    val products = viewModel.products

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    Column {
        Text("Products")
        Text("W-StoreMgData Product Module")

        AddProductForm(
            onSave = { product ->
                viewModel.addProduct(product)
            }
        )

        products.value.forEach { product ->
            Text(
                "${product.name} | ${product.stock}"
            )
        }
    }
}
