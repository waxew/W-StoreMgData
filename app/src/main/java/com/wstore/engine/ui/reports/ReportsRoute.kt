package com.wstore.engine.ui.reports

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Route ماژول گزارش‌ها.
 */
fun NavGraphBuilder.reportsRoute(
    onBack: () -> Unit
) {
    composable(ReportsModule.ROUTE) {
        val viewModel: ReportsViewModel = hiltViewModel()
        ReportsScreen(
            viewModel = viewModel,
            onBack = onBack
        )
    }
}
