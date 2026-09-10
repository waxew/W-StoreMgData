package business_profile.schema

/**
 * تعریف فیلدهای اختصاصی Business Profile.
 *
 * هدف:
 * جلوگیری از Hard Code شدن فیلدهای هر کسب و کار در Core.
 */
data class AttributeDefinition(
    val name: String,
    val type: String,
    val required: Boolean = false,
    val options: List<String> = emptyList()
)
