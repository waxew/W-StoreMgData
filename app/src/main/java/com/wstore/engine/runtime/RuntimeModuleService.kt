package com.wstore.engine.runtime

import com.wstore.engine.core.module.runtime.RuntimeModuleLoader
import core.domain.BusinessRegistry

/**
 * نام فایل: RuntimeModuleService.kt
 * ماژول: Runtime
 * وظیفه: اتصال Business Profile فعال به RuntimeModuleLoader هسته.
 *
 * این سرویس فقط لیست ماژول‌های فعال را از Profile می‌خواند و به Loader هسته می‌سپارد.
 * منطق اختصاصی هیچ نوع فروشگاه در این فایل قرار نمی‌گیرد.
 */
object RuntimeModuleService {

    private val loader = RuntimeModuleLoader()

    fun getActiveModules(): List<String> {
        val profile = BusinessRegistry.current()
            ?: return emptyList()

        return loader.load(profile.enabledModules)
    }

    fun hasModule(moduleId: String): Boolean {
        return moduleId in getActiveModules()
    }
}
