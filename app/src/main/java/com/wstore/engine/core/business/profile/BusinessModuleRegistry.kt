package com.wstore.engine.core.business.profile

/**
 * رجیستری ماژول های فعال سیستم.
 *
 * این کلاس مشخص می کند کدام قابلیت های عمومی سیستم
 * در Runtime فعال هستند.
 */
class BusinessModuleRegistry {

    private val modules = mutableListOf<BusinessModuleConfig>()

    fun register(module: BusinessModuleConfig) {
        modules.add(module)
    }

    fun isEnabled(moduleId: String): Boolean {
        return modules.any { it.moduleId == moduleId && it.enabled }
    }

    fun getModules(): List<BusinessModuleConfig> = modules
}
