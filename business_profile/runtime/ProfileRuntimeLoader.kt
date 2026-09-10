package business_profile.runtime

import business_profile.registry.BusinessProfileDefinition

/**
 * بارگذاری Profile فعال برای Runtime.
 * مسیر نهایی:
 * JSON Profile -> Registry -> Runtime -> UI
 */
class ProfileRuntimeLoader {
    fun load(profile: BusinessProfileDefinition): ProfileRuntimeState {
        return ProfileRuntimeState(
            profileId = profile.id,
            dashboard = profile.ui,
            modules = profile.modules
        )
    }
}
