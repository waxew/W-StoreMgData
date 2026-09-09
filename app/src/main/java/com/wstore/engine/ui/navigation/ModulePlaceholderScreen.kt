package com.wstore.engine.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * نام فایل: ModulePlaceholderScreen.kt
 * ماژول: Navigation
 * وظیفه: مقصد موقت برای ماژول‌های فعال Runtime که UI مستقل آنها هنوز تکمیل نشده است.
 *
 * این صفحه Feature را جایگزین نمی‌کند و فقط مانع Route شکسته در حین توسعه می‌شود.
 */
@Composable
fun ModulePlaceholderScreen(
    moduleId: String,
    onBack: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Module: $moduleId")
        Text(text = "این ماژول فعال است و رابط کاربری آن در مرحله توسعه قرار دارد.")
        Button(onClick = onBack) {
            Text("بازگشت")
        }
    }
}
