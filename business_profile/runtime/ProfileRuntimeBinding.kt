package business_profile.runtime

import business_profile.core.ProfileDefinition

/**
 * Shared runtime contract between profile engine, UI and backend.
 */
interface ProfileRuntimeBinding {
    fun activeProfile(): ProfileDefinition?
    fun hasFeature(feature: String): Boolean
}
