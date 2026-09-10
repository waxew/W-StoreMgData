package com.wstore.core.profile

/**
 * انتخاب پروفایل فعال برنامه
 */
class ActiveProfileResolver(
    private val registry: ProfileRegistry
) {
    fun resolve(profileId: String): ProfileDefinition? {
        return registry.getProfile(profileId)
    }
}
