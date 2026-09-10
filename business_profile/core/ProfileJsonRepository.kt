package com.wstore.core.profile

/**
 * Repository مرکزی برای دریافت Business Profile ها
 */
class ProfileJsonRepository(
    private val loader: ProfileLoader,
    private val validator: ProfileValidator
) {

    fun loadProfiles(source: List<ProfileDefinition>): List<ProfileDefinition> {
        return loader
            .loadAll(source)
            .filter { validator.isValid(it) }
    }
}
