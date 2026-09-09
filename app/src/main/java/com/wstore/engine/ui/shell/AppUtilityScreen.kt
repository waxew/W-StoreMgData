package com.wstore.engine.ui.shell

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wstore.engine.config.AppConfigRuntimeStore
import com.wstore.engine.profile.ProfileRuntimeStore
import com.wstore.engine.ui.navigation.AppUtilityRoute
import com.wstore.engine.update.UpdateCheckState
import com.wstore.engine.update.UpdateChecker
import com.wstore.engine.update.UpdateRuntimeStore

/**
 * نام فایل: AppUtilityScreen.kt
 * ماژول: App Shell
 * وظیفه: نمایش Screenهای مشترک Drawer بدون وابستگی به Business Profile خاص.
 *
 * اطلاعات برنامه، شرکت و نسخه از App Config و Profile Runtime خوانده می‌شوند و در UI تکرار نمی‌شوند.
 */
@Composable
fun AppUtilityScreen(
    destination: AppUtilityRoute,
    onBack: () -> Unit,
    onBackupRequested: () -> Unit = {},
    onRestoreRequested: () -> Unit = {}
) {
    val config = remember { AppConfigRuntimeStore.current() }
    val profile = remember { ProfileRuntimeStore.current() }
    val updateState by UpdateRuntimeStore.state.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(destination.title)
            TextButton(onClick = onBack) {
                Text("بازگشت")
            }
        }

        HorizontalDivider()

        when (destination) {
            AppUtilityRoute.Settings -> {
                Text("حالت نمایش: سیستم")
                Text("Drawer: ${if (config.ui.drawerEnabled) "فعال" else "غیرفعال"}")
                Text("Top Bar: ${if (config.ui.topBarEnabled) "فعال" else "غیرفعال"}")
                Text("Business Profile فعال: ${profile.name}")
            }

            AppUtilityRoute.Notifications -> {
                when (val state = updateState) {
                    is UpdateCheckState.Available -> {
                        Text("● نسخه جدید رسید: ${state.info.versionName}")
                        if (state.info.notes.isNotBlank()) {
                            Text(state.info.notes)
                        }
                    }
                    UpdateCheckState.Checking -> Text("در حال بررسی بروزرسانی...")
                    is UpdateCheckState.Failed -> Text("بررسی بروزرسانی ناموفق بود: ${state.message}")
                    else -> Text("شما اعلان جدیدی ندارید.")
                }
            }

            AppUtilityRoute.About -> {
                Text(config.app.name)
                Text("نسخه ${config.app.versionName}")
                Text("این برنامه بر پایه موتور فروشگاهی W-StoreMgData و Business Profile «${profile.name}» ساخته شده است.")
                Spacer(modifier = Modifier.height(12.dp))
                Text(config.company.displayName)
                Text("لوگوی شرکت از کلید ${config.company.logoKey} در Asset Profile خوانده می‌شود.")
            }

            AppUtilityRoute.Contact -> {
                Text(config.company.displayName)
                Text(
                    "AS Team زیرساخت توسعه و آموزش نرم‌افزارهای White Label این پروژه را نگهداری می‌کند. " +
                        "برای ارتباط فنی، گزارش مشکل یا پشتیبانی از اطلاعات زیر استفاده کنید."
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("ایمیل پشتیبانی:")
                Text(config.company.supportEmail)
            }

            AppUtilityRoute.Share -> {
                Text("می‌توانید ${config.app.name} را به دوستان معرفی کنید.")
                Button(
                    onClick = {
                        val shareIntent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(
                                Intent.EXTRA_TEXT,
                                "${config.app.name} — نسخه ${config.app.versionName}"
                            )
                        }
                        context.startActivity(
                            Intent.createChooser(shareIntent, "معرفی به دوستان")
                        )
                    }
                ) {
                    Text("اشتراک‌گذاری")
                }
            }

            AppUtilityRoute.Backup -> {
                Text("پشتیبان‌گیری از اطلاعات برنامه")
                Text("Backup شامل داده‌هایی می‌شود که در App Config فعال شده‌اند.")
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = onBackupRequested,
                        enabled = config.backup.enabled
                    ) {
                        Text("Backup")
                    }
                    Button(
                        onClick = onRestoreRequested,
                        enabled = config.backup.enabled
                    ) {
                        Text("Restore")
                    }
                }
            }

            AppUtilityRoute.Update -> {
                Text("نسخه فعلی: ${config.app.versionName} (${config.app.versionCode})")
                UpdateStateText(updateState)
                Button(
                    onClick = { UpdateChecker.checkAsync() },
                    enabled = config.update.enabled && updateState != UpdateCheckState.Checking
                ) {
                    Text("بررسی نسخه جدید")
                }
            }
        }
    }
}

@Composable
private fun UpdateStateText(state: UpdateCheckState) {
    when (state) {
        UpdateCheckState.Idle -> Text("وضعیت بروزرسانی هنوز بررسی نشده است.")
        UpdateCheckState.Checking -> Text("در حال بررسی نسخه جدید...")
        UpdateCheckState.Disabled -> Text("بررسی بروزرسانی در App Config غیرفعال است.")
        UpdateCheckState.NotConfigured -> Text("آدرس Manifest بروزرسانی هنوز در App Config تنظیم نشده است.")
        UpdateCheckState.UpToDate -> Text("برنامه به‌روز است.")
        is UpdateCheckState.Available -> {
            Text("نسخه جدید ${state.info.versionName} در دسترس است.")
            if (state.info.notes.isNotBlank()) {
                Text(state.info.notes)
            }
        }
        is UpdateCheckState.Failed -> Text("خطا: ${state.message}")
    }
}
