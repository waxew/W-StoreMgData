package business_profile.dashboard

/**
 * مدل Runtime داشبورد بر اساس Business Profile فعال
 */
data class DashboardRuntimeModel(
    val profileId: String,
    val dashboardType: String,
    val enabledModules: List<String>
)
