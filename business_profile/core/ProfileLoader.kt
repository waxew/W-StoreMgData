package com.wstore.core.profile

/**
 * Loader پروفایل از منبع JSON
 *
 * این لایه مسئول تبدیل تعریف خارجی پروفایل به مدل داخلی است.
 * منطق کسب و کار داخل Loader قرار نمی گیرد.
 */
class ProfileLoader {

    fun loadFromDefinition(profile: ProfileDefinition): ProfileDefinition {
        return profile
    }

    fun loadAll(profiles: List<ProfileDefinition>): List<ProfileDefinition> {
        return profiles.filter { it.enabled }
    }
}
