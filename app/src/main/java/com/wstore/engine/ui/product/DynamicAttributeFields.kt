package com.wstore.engine.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wstore.engine.profile.ProfileAttributeDefinition

/**
 * نام فایل: DynamicAttributeFields.kt
 * ماژول: Product UI / Dynamic Attribute Engine
 * وظیفه: تبدیل Metadata مربوط به Attributeهای Business Profile به فیلدهای Compose.
 *
 * این Renderer نوع کسب‌وکار را نمی‌شناسد؛ موبایل، بوتیک، آرایشی و Profileهای آینده
 * فقط از طریق ProfileAttributeDefinition رفتار و عنوان فیلدها را تعیین می‌کنند.
 */
@Composable
fun DynamicAttributeFields(
    definitions: List<ProfileAttributeDefinition>,
    values: Map<String, String>,
    onValueChange: (key: String, value: String) -> Unit
) {
    Column {
        definitions
            .filter { it.visible }
            .forEach { definition ->
                DynamicAttributeField(
                    definition = definition,
                    value = values[definition.key].orEmpty(),
                    onValueChange = { newValue ->
                        onValueChange(definition.key, newValue)
                    }
                )
            }
    }
}

@Composable
private fun DynamicAttributeField(
    definition: ProfileAttributeDefinition,
    value: String,
    onValueChange: (String) -> Unit
) {
    val label = if (definition.required) {
        "${definition.label} *"
    } else {
        definition.label
    }

    when (definition.type) {
        "boolean" -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = value.equals("true", ignoreCase = true),
                    onCheckedChange = { checked ->
                        onValueChange(checked.toString())
                    }
                )
                Text(
                    text = label,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        "option" -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(label)
                definition.options.forEach { option ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        RadioButton(
                            selected = value == option,
                            onClick = { onValueChange(option) }
                        )
                        Text(
                            text = option,
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }
            }
        }

        else -> {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                value = value,
                onValueChange = onValueChange,
                label = { Text(label) },
                supportingText = {
                    attributeTypeHint(definition.type)?.let { hint ->
                        Text(hint)
                    }
                },
                singleLine = true
            )
        }
    }
}

private fun attributeTypeHint(type: String): String? = when (type) {
    "number" -> "عدد صحیح"
    "decimal" -> "عدد اعشاری"
    "date" -> "تاریخ"
    "barcode" -> "بارکد"
    "serial" -> "شناسه/سریال"
    else -> null
}
