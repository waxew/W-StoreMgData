package com.wstore.engine.ui.attribute

/**
 * ماژول Lookup برای Featureهایی که داده اصلی آن‌ها در Dynamic Attribute Storage نگهداری می‌شود.
 *
 * IMEI و Warranty در پروفایل موبایل Attribute هستند؛ بنابراین برای نمایش و جستجوی آن‌ها
 * Product Core یا جدول جدید ساخته نمی‌شود.
 */
object ProductAttributeLookupModule {
    const val ARG_MODULE_ID = "moduleId"
    const val ROUTE_PATTERN = "attribute-lookup/{$ARG_MODULE_ID}"

    const val IMEI_MODULE_ID = "imei"
    const val WARRANTY_MODULE_ID = "warranty"

    val supportedModuleIds: Set<String> = setOf(
        IMEI_MODULE_ID,
        WARRANTY_MODULE_ID
    )

    fun routeFor(moduleId: String): String = "attribute-lookup/$moduleId"
}
