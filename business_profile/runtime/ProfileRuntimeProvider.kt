package com.wstore.core.profile

/**
 * تامین کننده Profile فعال برای مصرف UI و Backend
 */
class ProfileRuntimeProvider(
    private val runtimeManager: ProfileRuntimeManager
) {

    fun current(): ProfileDefinition? {
        return runtimeManager.activeProfile
    }
}
