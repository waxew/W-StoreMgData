package com.wstore.engine.ui.sales

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Route مربوط به Sales Module.
 */
fun NavGraphBuilder.salesRoute() {
    composable(SalesModule.ROUTE) {
        val viewModel: SalesViewModel = hiltViewModel()
        SalesScreen(viewModel = viewModel)
    }
}
