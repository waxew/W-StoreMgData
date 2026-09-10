package business_profile.ui

/**
 * تعریف صفحه پویا بر اساس Business Profile.
 * صفحه ها از Profile و Schema تغذیه می شوند و Hard Code نمی شوند.
 */
data class DynamicScreenDefinition(
    val screenId: String,
    val title: String,
    val components: List<String>
)
