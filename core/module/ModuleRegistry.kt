/*
================================================
نام فایل:
ModuleRegistry.kt

بخش:
Core Module Engine

وظیفه:
مدیریت ثبت و دسترسی به ماژول های نرم افزاری مستقل.

این فایل هیچ اطلاعی از نوع کسب و کار ندارد.
کسب و کارها فقط از طریق Business Profile مشخص می کنند
چه ماژول هایی فعال باشند.

مثال:
Warranty
Repair
Appointment
Advertisement

================================================
*/

package com.wstoremgdata.core.module

/**
 * رجیستری مرکزی ماژول ها
 */
object ModuleRegistry {

    private val modules = mutableMapOf<String, ModuleDefinition>()

    fun register(module: ModuleDefinition) {
        modules[module.id] = module
    }

    fun get(moduleId: String): ModuleDefinition? {
        return modules[moduleId]
    }

    fun getAll(): List<ModuleDefinition> {
        return modules.values.toList()
    }
}
