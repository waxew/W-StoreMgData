package com.wstore.engine.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

object AppThemeConfig {
    const val DEFAULT_THEME = "default"
}

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme {
        content()
    }
}
