package com.wstore.engine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.wstore.engine.ui.customer.CustomerModule
import com.wstore.engine.ui.customer.customerRoute
import com.wstore.engine.ui.dashboard.DashboardScreen
import com.wstore.engine.ui.dashboard.DashboardViewModel
import com.wstore.engine.ui.inventory.inventoryRoute
import com.wstore.engine.ui.invoice.invoiceRoute
import com.wstore.engine.ui.product.ProductScreen
import com.wstore.engine.ui.product.ProductViewModel
import com.wstore.engine.ui.reports.reportsRoute
import com.wstore.engine.ui.sales.SalesModule
import com.wstore.engine.ui.sales.salesRoute
import com.wstore.engine.ui.imei_warranty.AttributeLookupRoutes
import com.wstore.engine.ui.imei_warranty.AttributeLookupScreen

/**
 * نام فایل: AppNavHost.kt
 * وظیفه: اتصال Navigation Graph اصلی به Featureهای فعال.
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
            val viewModel: ProductViewModel = hiltViewModel()
            ProductScreen(viewModel = viewModel)
        }

        customerRoute(
            onCustomerSelected = { customerId ->
                navController.navigate(CustomerModule.detailRoute(customerId))
            },
            onSaleSelected = { saleId ->
                navController.navigate(SalesModule.detailRoute(saleId))
            },
            onBack = { navController.popBackStack() }
        )
        inventoryRoute()
        salesRoute(onBack = { navController.popBackStack() })
        invoiceRoute()
        reportsRoute(onBack = { navController.popBackStack() })

        composable(AttributeLookupRoutes.IMEI) {
            AttributeLookupScreen(
                title = "IMEI",
                values = emptyMap()
            )
        }

        composable(AttributeLookupRoutes.WARRANTY) {
            AttributeLookupScreen(
                title = "Warranty",
                values = emptyMap()
            )
        }

        composable(ScreenRoute.ModulePlaceholder.route) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId").orEmpty()
            ModulePlaceholderScreen(
                moduleId = moduleId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
