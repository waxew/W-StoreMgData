package com.wstore.engine.ui.customer

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

/**
 * Routeهای مربوط به Customer Module.
 */
fun NavGraphBuilder.customerRoute(
    onCustomerSelected: (Long) -> Unit,
    onSaleSelected: (Long) -> Unit,
    onBack: () -> Unit
) {
    composable(CustomerModule.ROUTE) {
        val viewModel: CustomerViewModel = hiltViewModel()
        CustomerScreen(
            viewModel = viewModel,
            onCustomerSelected = onCustomerSelected
        )
    }

    composable(
        route = CustomerModule.DETAIL_ROUTE,
        arguments = listOf(
            navArgument(CustomerModule.CUSTOMER_ID_ARGUMENT) {
                type = NavType.LongType
            }
        )
    ) {
        val viewModel: CustomerDetailViewModel = hiltViewModel()
        CustomerDetailScreen(
            viewModel = viewModel,
            onBack = onBack,
            onSaleSelected = onSaleSelected
        )
    }
}
