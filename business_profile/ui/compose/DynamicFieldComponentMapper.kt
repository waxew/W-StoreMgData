package business_profile.ui.compose

/**
 * مپ کردن نوع فیلدهای داینامیک پروفایل به کامپوننت‌های Compose
 * این فایل فقط مسئول انتخاب Component است و منطق کسب و کار ندارد.
 */
object DynamicFieldComponentMapper {
    fun resolve(fieldType: String): String {
        return when (fieldType.lowercase()) {
            "text" -> "TextField"
            "number" -> "NumberField"
            "boolean" -> "SwitchField"
            "select" -> "DropdownField"
            else -> "TextField"
        }
    }
}
