package com.wstore.core.profile

/**
 * اعتبارسنجی اولیه پروفایل
 */
class ProfileValidator {
    fun validate(profile: ProfileDefinition): Boolean {
        return profile.id.isNotBlank() &&
                profile.name.isNotBlank()
    }
}
