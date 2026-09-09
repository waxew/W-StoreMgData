package com.wstore.core.profile

/**
 * وضعیت Runtime پروفایل فعال برنامه
 */
class ProfileRuntimeState {

    private var activeProfile: ProfileDefinition? = null

    fun setActive(profile: ProfileDefinition) {
        activeProfile = profile
    }

    fun getActive(): ProfileDefinition? {
        return activeProfile
    }
}
