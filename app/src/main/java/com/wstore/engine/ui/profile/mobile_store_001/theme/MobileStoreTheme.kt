package com.wstore.engine.ui.profile.mobile_store_001.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * تم اختصاصی پروفایل فروشگاه موبایل.
 *
 * این فایل فقط ظاهر پروفایل mobile_store_001 را کنترل می‌کند.
 * منطق کسب‌وکار، داده و Repository نباید داخل Theme قرار بگیرد.
 */
private val MobileStoreLightColors = lightColorScheme(
    primary = Color(0xFF1565C0),
    secondary = Color(0xFF42A5F5),
    tertiary = Color(0xFF26A69A)
)

private val MobileStoreDarkColors = darkColorScheme(
    primary = Color(0xFF90CAF9),
    secondary = Color(0xFF64B5F6),
    tertiary = Color(0xFF80CBC4)
)

/**
 * Wrapper اصلی UI پروفایل موبایل.
 * در آینده رنگ‌ها می‌توانند از Profile Config خوانده شوند.
 */
@Composable
fun MobileStoreTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) {
            MobileStoreDarkColors
        } else {
            MobileStoreLightColors
        },
        content = content
    )
}
