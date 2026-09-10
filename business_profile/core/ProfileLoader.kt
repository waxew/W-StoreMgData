package com.wstore.core.profile

/**
 * Loader پروفایل از منبع JSON
 *
 * این لایه مسئول آماده سازی تعریف خارجی پروفایل برای Runtime است.
 * منطق کسب و کار داخل Loader قرار نمی گیرد.
 */
class ProfileLoader {

    fun loadFromDefinition(profile: ProfileDefinition): ProfileDefinition {
        return profile.copy(
            modules = profile.modules.distinct(),
            features = profile.features.distinct()
        )
    }

    fun loadAll(profiles: List<ProfileDefinition>): List<ProfileDefinition> {
        return profiles
            .map { loadFromDefinition(it) }
            .filter { it.enabled }
    }
}
