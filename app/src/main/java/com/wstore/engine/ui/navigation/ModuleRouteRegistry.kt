package com.wstore.engine.ui.navigation

import com.wstore.engine.ui.customer.CustomerModule
import com.wstore.engine.ui.inventory.InventoryModule
import com.wstore.engine.ui.invoice.InvoiceModule
import com.wstore.engine.ui.imei_warranty.AttributeLookupRoutes
import com.wstore.engine.ui.reports.ReportsModule
import com.wstore.engine.ui.sales.SalesModule

/**
 * نام فایل: ModuleRouteRegistry.kt
 * ماژول: Navigation
 * وظیفه: تبدیل شناسه ماژول Runtime به Route مناسب.
 *
 * Dashboard فقط شناسه ماژول را ارسال می‌کند و Route در این Registry Resolve می‌شود.
 */
object ModuleRouteRegistry {

    fun routeFor(moduleId: String): String {
        return when (moduleId) {
            "product" -> ScreenRoute.Product.route
            CustomerModule.ID -> CustomerModule.ROUTE
            InventoryModule.ID -> InventoryModule.ROUTE
            SalesModule.ID -> SalesModule.ROUTE
            InvoiceModule.ID -> InvoiceModule.ROUTE
            ReportsModule.ID -> ReportsModule.ROUTE
            "imei" -> AttributeLookupRoutes.IMEI
            "warranty" -> AttributeLookupRoutes.WARRANTY
            else -> ScreenRoute.ModulePlaceholder.createRoute(moduleId)
        }
    }
}
