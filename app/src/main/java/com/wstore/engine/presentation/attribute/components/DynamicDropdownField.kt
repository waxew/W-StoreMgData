package com.wstore.engine.presentation.attribute.components

import androidx.compose.runtime.Composable

/**
 * Dynamic dropdown component for schema driven attributes.
 * Used for enum based fields such as size, color or category.
 */
@Composable
fun DynamicDropdownField(
    label: String,
    options: List<String>,
    value: String,
    onValueChange: (String) -> Unit
) {
    // Compose dropdown implementation will be connected here.
    // Component remains business agnostic.
}
