package com.wstore.engine.runtime

import android.content.Context
import com.wstore.engine.profile.ActiveProfileResolver
import com.wstore.engine.profile.ProfileLoader
import com.wstore.engine.profile.ProfileRegistry
import com.wstore.engine.profile.ProfileRuntimeStore
import core.domain.BusinessProfile
import core.domain.BusinessRegistry

/**
 * نام فایل: BusinessRuntimeInitializer.kt
 * ماژول: Runtime / Business Profile
 * وظیفه: بارگذاری Profileهای Config، انتخاب تنها Profile فعال و اتصال آن به Core Runtime.
 *
 * اصل معماری:
 * این فایل هیچ نام کسب‌وکار مشخصی را Hard Code نمی‌کند. Profile فعال فقط از enabled=true
 * در فایل‌های business_profiles تعیین می‌شود.
 */
object BusinessRuntimeInitializer {

    fun initialize(context: Context) {
        val profiles = ProfileLoader(context.applicationContext).loadAll()
        val registry = ProfileRegistry(profiles)
        val activeProfile = ActiveProfileResolver().resolve(registry)

        // مدل کامل Profile برای UI/Theme/Feature Engine در App نگهداری می‌شود.
        ProfileRuntimeStore.register(activeProfile)

        // قرارداد ساده Core همچنان حفظ می‌شود تا Featureهای فعلی پروژه بدون حذف یا بازنویسی کار کنند.
        BusinessRegistry.register(
            BusinessProfile(
                id = activeProfile.id,
                name = activeProfile.name,
                businessType = activeProfile.businessType,
                enabledModules = activeProfile.enabledModuleIds(),
                attributes = activeProfile.attributes.associate { attribute ->
                    attribute.key to attribute.type
                }
            )
        )
    }
}
