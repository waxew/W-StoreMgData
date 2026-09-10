package business_profile.ui.compose

import androidx.compose.runtime.Composable

/**
 * رندر فرم پویا بر اساس Schema پروفایل کسب و کار.
 * این کلاس فقط مسئول نمایش است و منطق ذخیره سازی را انجام نمی دهد.
 */
@Composable
fun DynamicFormComposeRenderer(
    fields: List<DynamicUiField>,
    onValueChanged: (String, String) -> Unit
) {
    fields.forEach { field ->
        DynamicFieldComponent(
            field = field,
            onValueChanged = onValueChanged
        )
    }
}

/** مدل نمایش فیلدهای Runtime */
data class DynamicUiField(
    val key: String,
    val label: String,
    val type: String,
    val value: String = ""
)

@Composable
private fun DynamicFieldComponent(
    field: DynamicUiField,
    onValueChanged: (String, String) -> Unit
) {
    // Component mapping در لایه UI تکمیل می شود.
}
