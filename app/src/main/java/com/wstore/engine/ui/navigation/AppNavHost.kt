package com.wstore.engine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.wstore.engine.ui.dashboard.DashboardScreen
import com.wstore.engine.ui.product.ProductScreen
import com.wstore.engine.ui.product.ProductViewModel

/**
 * نام فایل: AppNavHost.kt
 * ماژول: Navigation
 * وظیفه: تعریف Navigation Graph اصلی برنامه و اتصال Routeها به Feature Screenها.
 *
 * Dashboard فقط شناسه Module را ارسال می‌کند و Route از ModuleRouteRegistry دریافت می‌شود.
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenRoute.Dashboard.route
    ) {
        composable(ScreenRoute.Dashboard.route) {
            DashboardScreen(
                onModuleSelected = { moduleId ->
                    navController.navigate(ModuleRouteRegistry.routeFor(moduleId))
                }
            )
        }

        composable(ScreenRoute.Product.route) {
            val viewModel: ProductViewModel = hiltViewModel()
            ProductScreen(viewModel = viewModel)
        }

        composable(
            route = ScreenRoute.ModulePlaceholder.route,
            arguments = listOf(navArgument("moduleId") { type = NavType.StringType })
        ) { backStackEntry ->
            val moduleId = backStackEntry.arguments?.getString("moduleId").orEmpty()
            ModulePlaceholderScreen(
                moduleId = moduleId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
