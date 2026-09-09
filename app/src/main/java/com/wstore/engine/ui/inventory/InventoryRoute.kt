package com.wstore.engine.ui.inventory

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Route مربوط به Inventory Module.
 */
fun NavGraphBuilder.inventoryRoute() {
    composable(InventoryModule.ROUTE) {
        val viewModel: InventoryViewModel = hiltViewModel()
        InventoryScreen(viewModel = viewModel)
    }
}
