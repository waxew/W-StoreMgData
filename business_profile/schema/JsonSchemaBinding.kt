package business_profile.schema

/**
 * اتصال Schema تعریف شده در JSON به موتور Dynamic Attribute.
 *
 * مسئولیت:
 * - تبدیل تعریف JSON به مدل داخلی
 * - آماده سازی Attribute ها برای UI و Backend
 * - جلوگیری از وابستگی Profile به Core
 */
class JsonSchemaBinding {
    fun bind(attributes: List<AttributeDefinition>): List<AttributeDefinition> {
        return attributes.distinctBy { it.key }
    }
}
