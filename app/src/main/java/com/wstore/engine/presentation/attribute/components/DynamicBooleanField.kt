package com.wstore.engine.presentation.attribute.components

import androidx.compose.runtime.Composable

/**
 * Dynamic boolean component for schema driven attributes.
 * Used for flags like active, available or warranty enabled.
 */
@Composable
fun DynamicBooleanField(
    label: String,
    value: Boolean,
    onValueChange: (Boolean) -> Unit
) {
    // Compose switch implementation will be connected here.
    // Component remains independent from business profiles.
}
