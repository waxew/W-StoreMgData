package business_profile.runtime

/**
 * اتصال Profile بارگذاری شده به Runtime برنامه
 */
class ProfileRuntimeBinder {
    fun bind(profileId: String): ProfileRuntimeState {
        return ProfileRuntimeState(profileId = profileId)
    }
}
