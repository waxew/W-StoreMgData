package com.wstore.engine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wstore.engine.ui.customer.CustomerModule
import com.wstore.engine.ui.customer.customerRoute
import com.wstore.engine.ui.dashboard.DashboardScreen
import com.wstore.engine.ui.dashboard.DashboardViewModel
import com.wstore.engine.ui.inventory.inventoryRoute
import com.wstore.engine.ui.invoice.invoiceRoute
import com.wstore.engine.ui.imei_warranty.AttributeLookupRoutes
import com.wstore.engine.ui.imei_warranty.AttributeLookupScreen
import com.wstore.engine.ui.imei_warranty.AttributeLookupViewModel
import com.wstore.engine.ui.product.ProductScreen
import com.wstore.engine.ui.product.ProductViewModel
import com.wstore.engine.ui.reports.reportsRoute
import com.wstore.engine.ui.sales.SalesModule
import com.wstore.engine.ui.sales.salesRoute

/**
 * Navigation Graph اصلی برنامه.
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Dashboard.route
    ) {
        composable(ScreenRoute.Dashboard.route) {
            val dashboardViewModel: DashboardViewModel = hiltViewModel()
            DashboardScreen(
                viewModel = dashboardViewModel,
                onModuleSelected = { moduleId ->
                    navController.navigate(ModuleRouteRegistry.routeFor(moduleId))
                },
                onSaleSelected = { saleId ->
                    navController.navigate(SalesModule.detailRoute(saleId))
                }
            )
        }

        composable(ScreenRoute.Product.route) {
            ProductScreen(viewModel = hiltViewModel<ProductViewModel>())
        }

        customerRoute(
            onCustomerSelected = { customerId -> navController.navigate(CustomerModule.detailRoute(customerId)) },
            onSaleSelected = { saleId -> navController.navigate(SalesModule.detailRoute(saleId)) },
            onBack = { navController.popBackStack() }
        )

        inventoryRoute()
        salesRoute(onBack = { navController.popBackStack() })
        invoiceRoute()
        reportsRoute(onBack = { navController.popBackStack() })

        composable(AttributeLookupRoutes.IMEI) {
            val viewModel: AttributeLookupViewModel = hiltViewModel()
            AttributeLookupScreen(
                title = "IMEI",
                viewModel = viewModel
            )
        }

        composable(AttributeLookupRoutes.WARRANTY) {
            val viewModel: AttributeLookupViewModel = hiltViewModel()
            AttributeLookupScreen(
                title = "Warranty",
                viewModel = viewModel
            )
        }
    }
}
