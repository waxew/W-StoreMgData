package com.wstore.engine.ui.reports

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.wstore.engine.profile.ProfileRuntimeStore
import com.wstore.engine.ui.profile.mobile_store_001.reports.MobileReportsScreen
import com.wstore.engine.ui.profile.mobile_store_001.reports.MobileReportsViewModel

/**
 * Route ماژول گزارش‌ها.
 *
 * Renderer از Business Profile فعال انتخاب می‌شود و Generic Reports به‌عنوان fallback حفظ شده است.
 */
fun NavGraphBuilder.reportsRoute(
    onBack: () -> Unit
) {
    composable(ReportsModule.ROUTE) {
        val profile = ProfileRuntimeStore.currentOrNull()

        if (profile?.uiProfile?.navigationVariant == "mobile_navigation") {
            val mobileViewModel: MobileReportsViewModel = hiltViewModel()
            MobileReportsScreen(
                viewModel = mobileViewModel,
                onBack = onBack
            )
        } else {
            val viewModel: ReportsViewModel = hiltViewModel()
            ReportsScreen(
                viewModel = viewModel,
                onBack = onBack
            )
        }
    }
}
