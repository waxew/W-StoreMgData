package com.wstore.engine.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun ProductScreen(
    viewModel: ProductViewModel
) {
    val products by viewModel.products.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadProducts()
    }

    Column {
        Text("Products")
        Text("W-StoreMgData Product Module")

        AddProductForm(
            onSave = { product ->
                viewModel.addProduct(
                    ProductEvent.AddProduct(
                        name = product.name,
                        code = product.code,
                        category = product.category,
                        price = product.price,
                        stock = product.stock
                    )
                )
            }
        )

        products.forEach { product ->
            Text("${product.name} | ${product.stock}")
        }
    }
}
