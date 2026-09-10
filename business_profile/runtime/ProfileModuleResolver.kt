package com.wstore.business_profile.runtime

/**
 * Resolver ماژول‌های فعال بر اساس Business Profile.
 *
 * UI و Backend نباید مستقیماً Profile را بررسی کنند.
 * این لایه نقطه مرکزی تصمیم‌گیری است.
 */
class ProfileModuleResolver {

    fun isModuleEnabled(
        enabledModules: List<String>,
        moduleName: String
    ): Boolean {
        return enabledModules.contains(moduleName)
    }
}
