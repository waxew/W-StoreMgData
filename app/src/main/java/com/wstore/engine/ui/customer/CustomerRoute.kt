package com.wstore.engine.ui.customer

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Route مربوط به Customer Module.
 * ViewModel از Hilt دریافت می‌شود تا UI به جزئیات ساخت وابستگی‌ها متصل نشود.
 */
fun NavGraphBuilder.customerRoute() {
    composable(CustomerModule.ROUTE) {
        val viewModel: CustomerViewModel = hiltViewModel()
        CustomerScreen(viewModel = viewModel)
    }
}
