package com.wstore.engine.presentation.attribute

import androidx.compose.runtime.Composable
import com.wstore.engine.core.attribute.AttributeDefinition

/**
 * Dynamic form renderer entry point.
 *
 * Receives attribute definitions from runtime schema and delegates
 * rendering to dynamic attribute components.
 *
 * This layer does not know business types such as mobile, boutique,
 * or beauty. All behavior is driven by AttributeDefinition metadata.
 */
@Composable
fun DynamicAttributeForm(
    attributes: List<AttributeDefinition>
) {
    attributes.forEach { attribute ->
        DynamicAttributeField(
            definition = attribute
        )
    }
}
