package com.wstore.engine.runtime

import core.domain.BusinessProfile
import core.domain.BusinessRegistry

/**
 * راه‌اندازی Runtime کسب و کار.
 *
 * این بخش فقط Profile را فعال می‌کند.
 * منطق اختصاصی فروشگاه‌ها نباید در App قرار بگیرد.
 */
object BusinessRuntimeInitializer {

    fun initialize() {
        val defaultProfile = BusinessProfile(
            id = "mobile_store_001",
            name = "Mobile Store",
            businessType = "MOBILE_STORE",
            enabledModules = listOf(
                "customer",
                "product",
                "inventory",
                "sales",
                "invoice",
                "reports",
                "imei",
                "warranty"
            ),
            attributes = mapOf(
                "product.brand" to "enabled",
                "product.storage" to "enabled",
                "product.color" to "enabled"
            )
        )

        BusinessRegistry.register(defaultProfile)
    }
}
