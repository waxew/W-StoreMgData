package com.wstore.engine.profile

/*
نام فایل:
ProfileRegistry.kt

ماژول:
Business Profile Engine

وظیفه:
ثبت و دسترسی فقط‌خواندنی به Profileهای بارگذاری‌شده از Config.
Registry هیچ Profile خاصی را Hard Code نمی‌کند و فقط روی قرارداد ProfileDefinition کار می‌کند.

قانون:
شناسه Profile باید یکتا باشد؛ وجود شناسه تکراری خطای پیکربندی محسوب می‌شود.
*/

class ProfileRegistry(
    profiles: List<ProfileDefinition>
) {
    private val profilesById: Map<String, ProfileDefinition>

    init {
        val duplicatedIds = profiles
            .groupingBy { it.id }
            .eachCount()
            .filterValues { count -> count > 1 }
            .keys

        require(duplicatedIds.isEmpty()) {
            "شناسه Profile تکراری است: ${duplicatedIds.joinToString()}"
        }

        profilesById = profiles.associateBy { it.id }
    }

    /** تمام Profileهای معتبر و بارگذاری‌شده را برمی‌گرداند. */
    fun allProfiles(): List<ProfileDefinition> = profilesById.values.toList()

    /** فقط Profileهایی را برمی‌گرداند که توسعه‌دهنده enabled=true کرده است. */
    fun enabledProfiles(): List<ProfileDefinition> =
        profilesById.values.filter { it.enabled }

    /** پیدا کردن Profile با شناسه یکتا. */
    fun findById(id: String): ProfileDefinition? = profilesById[id]
}
