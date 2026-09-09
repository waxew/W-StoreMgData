package com.wstore.engine.profile

/*
نام فایل:
ProfileRuntimeStore.kt

ماژول:
Business Profile Engine

وظیفه:
نگهداری تعریف کامل Profile فعال برای لایه‌های UI، Theme، Asset و Feature Engine.
این Store جایگزین BusinessRegistry نیست؛ BusinessRegistry همچنان قرارداد ساده Core را نگهداری می‌کند.
*/

object ProfileRuntimeStore {
    private var activeProfile: ProfileDefinition? = null

    fun register(profile: ProfileDefinition) {
        activeProfile = profile
    }

    fun current(): ProfileDefinition =
        checkNotNull(activeProfile) {
            "Profile Runtime هنوز مقداردهی اولیه نشده است."
        }

    fun currentOrNull(): ProfileDefinition? = activeProfile

    fun hasFeature(featureId: String): Boolean =
        featureId in current().features

    fun visibleAttributes(): List<ProfileAttributeDefinition> =
        current().attributes.filter { it.visible }
}
