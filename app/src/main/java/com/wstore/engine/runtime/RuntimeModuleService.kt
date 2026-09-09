package com.wstore.engine.runtime

import core.domain.BusinessRegistry
import com.wstoremgdata.core.module.ModuleType
import com.wstoremgdata.core.module.runtime.RuntimeModuleLoader

/**
 * سرویس اتصال Business Profile به Module Runtime.
 *
 * این لایه فقط تصمیم می‌گیرد چه قابلیت‌هایی فعال شوند.
 * منطق کسب‌وکار داخل Core باقی می‌ماند.
 */
class RuntimeModuleService {

    private val loader = RuntimeModuleLoader()

    fun activeModules(): List<String> {
        val profile = BusinessRegistry.getActive()
            ?: return emptyList()

        return loader.load(profile.enabledModules)
    }

    fun hasModule(moduleId: String): Boolean {
        return activeModules().contains(moduleId)
    }
}
