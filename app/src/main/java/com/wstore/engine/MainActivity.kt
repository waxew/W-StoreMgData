package com.wstore.engine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import com.wstore.engine.ui.product.ProductScreen
import com.wstore.engine.ui.product.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(b: Bundle?) {
        super.onCreate(b)
        setContent {
            val viewModel: ProductViewModel = hiltViewModel()
            ProductScreen(viewModel = viewModel)
        }
    }
}
