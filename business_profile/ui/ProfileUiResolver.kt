package com.wstore.core.profile

/**
 * Resolver مشترک برای انتخاب UI بر اساس Profile فعال
 */
class ProfileUiResolver {

    fun resolve(profile: ProfileDefinition): String {
        return profile.uiProfile
    }
}
