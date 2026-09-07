package com.wstore.engine.core.business.profile

/**
 * تشخیص قابلیت های فعال یک Business Profile.
 *
 * این لایه باعث می شود Core از نوع فروشگاه مستقل باقی بماند.
 */
class BusinessFeatureResolver {

    fun isEnabled(profile: BusinessProfile, featureId: String): Boolean {
        return profile.features.contains(featureId)
    }
}
