package com.wstore.engine.ui.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.profile.ProfileRuntimeStore
import com.wstore.engine.runtime.RuntimeModuleService
import com.wstore.engine.ui.profile.mobile_store_001.dashboard.MobileDashboardScreen

/**
 * داشبورد مدیریتی برنامه.
 *
 * ماژول‌های فعال همچنان از Runtime می‌آیند و آمار فقط از DashboardViewModel خوانده می‌شود.
 * انتخاب ظاهر Dashboard نیز از uiProfile پروفایل فعال انجام می‌شود؛ بنابراین Core Dashboard
 * نام کسب‌وکار را Hard Code نمی‌کند و در صورت اضافه شدن Renderer جدید، Generic fallback حفظ می‌شود.
 */
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel,
    onModuleSelected: (String) -> Unit,
    onSaleSelected: (Long) -> Unit
) {
    val state by viewModel.uiState.collectAsState()
    val modules = RuntimeModuleService.getActiveModules()
    val profile = ProfileRuntimeStore.currentOrNull()

    if (profile?.uiProfile?.dashboardVariant == "mobile_dashboard") {
        MobileDashboardScreen(
            state = state,
            activeModules = modules,
            onModuleSelected = onModuleSelected,
            onSaleSelected = onSaleSelected,
            storeName = profile.name
        )
        return
    }

    GenericDashboardContent(
        state = state,
        modules = modules,
        onModuleSelected = onModuleSelected,
        onSaleSelected = onSaleSelected
    )
}

/**
 * fallback عمومی برای Profileهایی که Renderer اختصاصی آن‌ها هنوز تکمیل نشده است.
 * این بخش عمداً حفظ شده تا تکمیل UI پروفایل‌ها باعث حذف قابلیت‌های فعلی نشود.
 */
@Composable
private fun GenericDashboardContent(
    state: DashboardUiState,
    modules: List<String>,
    onModuleSelected: (String) -> Unit,
    onSaleSelected: (Long) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text("داشبورد مدیریت فروشگاه")

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.error?.let { message ->
            Text(message)
        }

        DashboardKpiCard("مشتریان", state.customerCount.toString())
        DashboardKpiCard("کالاها", state.productCount.toString())
        DashboardKpiCard("تعداد فروش", state.salesCount.toString())
        DashboardKpiCard("مجموع فروش", state.totalRevenue.toString())
        DashboardKpiCard("کالاهای کم‌موجودی (۵ یا کمتر)", state.lowStockCount.toString())

        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Text("دسترسی سریع")

        QuickAction(
            moduleId = "product",
            title = "مدیریت کالا",
            activeModules = modules,
            onModuleSelected = onModuleSelected
        )
        QuickAction(
            moduleId = "customer",
            title = "مدیریت مشتری",
            activeModules = modules,
            onModuleSelected = onModuleSelected
        )
        QuickAction(
            moduleId = "sales",
            title = "ثبت و مشاهده فروش",
            activeModules = modules,
            onModuleSelected = onModuleSelected
        )
        QuickAction(
            moduleId = "invoice",
            title = "فاکتورها",
            activeModules = modules,
            onModuleSelected = onModuleSelected
        )
        QuickAction(
            moduleId = "reports",
            title = "گزارش‌های فروش و موجودی",
            activeModules = modules,
            onModuleSelected = onModuleSelected
        )

        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Text("فروش‌های اخیر")

        if (!state.isLoading && state.recentSales.isEmpty()) {
            Text("هنوز فروشی ثبت نشده است.")
        }

        state.recentSales.forEach { sale ->
            Button(
                onClick = { onSaleSelected(sale.id) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "فروش #${sale.id} | ${sale.customerName} | مبلغ: ${sale.totalAmount}"
                )
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        HorizontalDivider()
        Text("ماژول‌های فعال")

        modules.forEach { module ->
            DashboardModuleCard(
                moduleName = moduleLabel(module),
                onClick = { onModuleSelected(module) }
            )
        }
    }
}

@Composable
private fun DashboardKpiCard(
    title: String,
    value: String
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title)
            Text(value)
        }
    }
}

@Composable
private fun QuickAction(
    moduleId: String,
    title: String,
    activeModules: List<String>,
    onModuleSelected: (String) -> Unit
) {
    if (moduleId in activeModules) {
        Button(
            onClick = { onModuleSelected(moduleId) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(title)
        }
    }
}

private fun moduleLabel(moduleId: String): String = when (moduleId) {
    "product" -> "کالاها"
    "customer" -> "مشتریان"
    "inventory" -> "موجودی"
    "sales" -> "فروش"
    "invoice" -> "فاکتورها"
    "reports" -> "گزارش‌ها"
    "imei" -> "IMEI"
    "warranty" -> "گارانتی"
    else -> moduleId
}
