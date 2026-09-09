package com.wstore.engine.ui.customer

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

/**
 * Route مربوط به Customer Module.
 */
fun NavGraphBuilder.customerRoute() {
    composable("customer") {
        CustomerScreen()
    }
}
