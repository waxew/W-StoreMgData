package core.domain

/**
 * نام فایل: BusinessRegistry.kt
 * ماژول: Core Domain
 * وظیفه: مدیریت پروفایل فعال کسب و کار
 */

object BusinessRegistry {

    private var activeProfile: BusinessProfile? = null

    fun register(profile: BusinessProfile) {
        activeProfile = profile
    }

    fun current(): BusinessProfile? {
        return activeProfile
    }
}
