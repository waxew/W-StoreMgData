package business_profile.dashboard

/**
 * تعریف استاندارد صفحه داشبورد بر اساس Business Profile.
 */
data class DashboardScreenDefinition(
    val profileId: String,
    val screenId: String,
    val modules: List<String>
)
