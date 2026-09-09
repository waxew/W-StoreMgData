package com.wstore.engine.profile

/*
نام فایل:
ProfileRegistry.kt

ماژول:
Business Profile Engine

وظیفه:
مدیریت لیست پروفایل‌های قابل استفاده برنامه.

هدف:
اضافه شدن کسب و کار جدید فقط با اضافه کردن Definition انجام شود و Core تغییر نکند.
*/

class ProfileRegistry(
    private val profiles: List<ProfileDefinition>
) {

    // فقط پروفایل‌هایی که توسط توسعه‌دهنده فعال شده‌اند برگردانده می‌شوند.
    fun enabledProfiles(): List<ProfileDefinition> {
        return profiles.filter { it.enabled }
    }

    // پیدا کردن یک پروفایل با شناسه یکتا.
    fun findById(id: String): ProfileDefinition? {
        return profiles.firstOrNull { it.id == id }
    }
}
