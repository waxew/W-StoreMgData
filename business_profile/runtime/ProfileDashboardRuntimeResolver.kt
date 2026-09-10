package business_profile.runtime

/**
 * تعیین داشبورد فعال بر اساس Business Profile.
 * UI از این Resolver برای انتخاب Screen استفاده می کند.
 */
class ProfileDashboardRuntimeResolver {

    fun resolve(profileId: String): DashboardRuntimeDefinition {
        return DashboardRuntimeDefinition(
            profileId = profileId,
            dashboardKey = "${profileId}_dashboard"
        )
    }
}

data class DashboardRuntimeDefinition(
    val profileId: String,
    val dashboardKey: String
)
