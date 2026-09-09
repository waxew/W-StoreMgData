package com.wstore.engine.ui.invoice

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Route مربوط به Invoice Module.
 */
fun NavGraphBuilder.invoiceRoute() {
    composable(InvoiceModule.ROUTE) {
        val viewModel: InvoiceViewModel = hiltViewModel()
        InvoiceScreen(viewModel = viewModel)
    }
}
