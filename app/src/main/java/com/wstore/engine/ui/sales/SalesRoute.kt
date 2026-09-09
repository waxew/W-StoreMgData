package com.wstore.engine.ui.sales

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

/**
 * Routeهای مربوط به Sales Module.
 */
fun NavGraphBuilder.salesRoute(
    onSaleSelected: (Long) -> Unit,
    onBack: () -> Unit
) {
    composable(SalesModule.ROUTE) {
        val viewModel: SalesViewModel = hiltViewModel()
        SalesScreen(
            viewModel = viewModel,
            onSaleSelected = onSaleSelected
        )
    }

    composable(
        route = SalesModule.DETAIL_ROUTE,
        arguments = listOf(
            navArgument(SalesModule.SALE_ID_ARGUMENT) {
                type = NavType.LongType
            }
        )
    ) {
        val viewModel: SaleDetailViewModel = hiltViewModel()
        SaleDetailScreen(
            viewModel = viewModel,
            onBack = onBack
        )
    }
}
