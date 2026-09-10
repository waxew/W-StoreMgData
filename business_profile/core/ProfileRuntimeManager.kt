package business_profile.core

/**
 * Runtime holder for currently selected business profile.
 * پروفایل فعال برنامه از این مسیر به UI و Backend داده می شود.
 */
class ProfileRuntimeManager(
    private val resolver: ActiveProfileResolver
) {
    private var activeProfile: ProfileDefinition? = null

    fun activate(profileId: String, profiles: List<ProfileDefinition>) {
        activeProfile = resolver.resolve(profileId, profiles)
    }

    fun current(): ProfileDefinition? = activeProfile
}
