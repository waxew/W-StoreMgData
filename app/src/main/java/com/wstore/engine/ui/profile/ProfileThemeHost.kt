package com.wstore.engine.ui.profile

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.wstore.engine.profile.ProfileRuntimeStore
import com.wstore.engine.profile.ProfileUiVariants
import com.wstore.engine.ui.profile.mobile_store_001.theme.MobileStoreTheme

/**
 * میزبان Theme برنامه بر اساس Business Profile فعال.
 *
 * Theme در سطح App Shell انتخاب می‌شود تا تمام Screenهای یک Profile ظاهر هماهنگ داشته باشند.
 * Featureها و Repositoryها به Theme وابسته نیستند و Generic MaterialTheme نیز به‌عنوان fallback
 * حفظ می‌شود تا اضافه شدن Rendererهای بعدی به Core لطمه نزند.
 */
@Composable
fun ProfileThemeHost(
    content: @Composable () -> Unit
) {
    val profile = ProfileRuntimeStore.currentOrNull()
    val darkTheme = isSystemInDarkTheme()

    when (profile?.uiProfile?.navigationVariant) {
        ProfileUiVariants.MOBILE_NAVIGATION -> {
            MobileStoreTheme(
                darkTheme = darkTheme,
                content = content
            )
        }

        else -> {
            MaterialTheme(content = content)
        }
    }
}
