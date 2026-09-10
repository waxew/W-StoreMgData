package com.wstore.engine.ui.profile.mobile_store_001.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wstore.engine.ui.dashboard.DashboardUiState
import com.wstore.engine.ui.profile.mobile_store_001.components.MobileModuleCard
import com.wstore.engine.ui.profile.mobile_store_001.components.MobileStatisticCard

/**
 * نام فایل: MobileDashboardScreen.kt
 * پروفایل: mobile_store_001
 * وظیفه: داشبورد اختصاصی فروشگاه موبایل بر اساس مرجع گرافیکی تاییدشده.
 *
 * تمام KPIها از Dashboard backend می‌آیند و فعال بودن میانبرها از Runtime Profile تعیین می‌شود.
 * این صفحه هیچ منطق فروش، موجودی یا دیتابیس را داخل Compose پیاده‌سازی نمی‌کند.
 */
@Composable
fun MobileDashboardScreen(
    state: DashboardUiState,
    activeModules: List<String>,
    onModuleSelected: (String) -> Unit,
    onSaleSelected: (Long) -> Unit,
    modifier: Modifier = Modifier,
    storeName: String = "فروشگاه موبایل"
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = storeName,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "نمای کلی فروش، کالا و موجودی",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MobileStatisticCard(
                title = "فروش کل",
                value = state.totalRevenue.toString(),
                subtitle = "بر اساس فروش‌های ثبت‌شده",
                modifier = Modifier.weight(1f)
            )
            MobileStatisticCard(
                title = "کالاها",
                value = state.productCount.toString(),
                subtitle = "${state.lowStockCount} کم‌موجودی",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MobileStatisticCard(
                title = "مشتریان",
                value = state.customerCount.toString(),
                modifier = Modifier.weight(1f)
            )
            MobileStatisticCard(
                title = "تعداد فروش",
                value = state.salesCount.toString(),
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider()
        Text(
            text = "دسترسی سریع",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ModuleShortcut(
                moduleId = "product",
                title = "کالاها",
                subtitle = "مدیریت موبایل و لوازم جانبی",
                activeModules = activeModules,
                onModuleSelected = onModuleSelected,
                modifier = Modifier.weight(1f)
            )
            ModuleShortcut(
                moduleId = "inventory",
                title = "انبار",
                subtitle = "ورود، خروج و موجودی لحظه‌ای",
                activeModules = activeModules,
                onModuleSelected = onModuleSelected,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ModuleShortcut(
                moduleId = "sales",
                title = "فروش",
                subtitle = "ثبت فروش و سبد کالا",
                activeModules = activeModules,
                onModuleSelected = onModuleSelected,
                modifier = Modifier.weight(1f)
            )
            ModuleShortcut(
                moduleId = "customer",
                title = "مشتریان",
                subtitle = "پروفایل و سابقه خرید",
                activeModules = activeModules,
                onModuleSelected = onModuleSelected,
                modifier = Modifier.weight(1f)
            )
        }

        HorizontalDivider()
        Text(
            text = "فروش‌های اخیر",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        if (!state.isLoading && state.recentSales.isEmpty()) {
            Text(
                text = "هنوز فروشی ثبت نشده است.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        state.recentSales.take(5).forEach { sale ->
            MobileModuleCard(
                title = "فروش #${sale.id}",
                subtitle = "${sale.customerName} • ${sale.totalAmount}",
                onClick = { onSaleSelected(sale.id) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun ModuleShortcut(
    moduleId: String,
    title: String,
    subtitle: String,
    activeModules: List<String>,
    onModuleSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (moduleId in activeModules) {
        MobileModuleCard(
            title = title,
            subtitle = subtitle,
            onClick = { onModuleSelected(moduleId) },
            modifier = modifier
        )
    }
}
