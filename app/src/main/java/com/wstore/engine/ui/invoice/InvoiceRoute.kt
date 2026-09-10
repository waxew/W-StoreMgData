package com.wstore.engine.ui.invoice

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wstore.engine.profile.ProfileRuntimeStore
import com.wstore.engine.ui.profile.mobile_store_001.invoice.MobileInvoiceManagementScreen

/**
 * Route مربوط به Invoice Module.
 *
 * Renderer بر اساس Business Profile فعال انتخاب می‌شود و Generic Invoice به‌عنوان fallback حفظ می‌شود.
 */
fun NavGraphBuilder.invoiceRoute() {
    composable(InvoiceModule.ROUTE) {
        val viewModel: InvoiceViewModel = hiltViewModel()
        val profile = ProfileRuntimeStore.currentOrNull()

        if (profile?.uiProfile?.navigationVariant == "mobile_navigation") {
            MobileInvoiceManagementScreen(viewModel = viewModel)
        } else {
            InvoiceScreen(viewModel = viewModel)
        }
    }
}
