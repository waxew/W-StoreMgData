package com.wstore.core.profile

/**
 * Registry مرکزی پروفایل های کسب و کار
 */
class ProfileRegistry {
    private val profiles = mutableMapOf<String, ProfileDefinition>()

    fun register(profile: ProfileDefinition) {
        profiles[profile.id] = profile
    }

    fun getProfile(id: String): ProfileDefinition? = profiles[id]

    fun getEnabledProfiles(): List<ProfileDefinition> =
        profiles.values.filter { it.enabled }
}
