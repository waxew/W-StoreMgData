package business_profile.runtime

/**
 * تبدیل Profile Definition به Runtime State.
 * این بخش پل بین فایل های پروفایل و UI/Backend است.
 */
class ProfileRuntimeMapper {
    fun map(
        profileId: String,
        modules: List<String>,
        themeId: String,
        dashboardId: String
    ): ProfileRuntimeState {
        return ProfileRuntimeState(
            profileId = profileId,
            enabledModules = modules,
            themeId = themeId,
            dashboardId = dashboardId
        )
    }
}
