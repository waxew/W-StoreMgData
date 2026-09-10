package com.wstore.engine.ui.imei_warranty

/**
 * قرارداد دسترسی صفحه IMEI/Warranty به Dynamic Attribute Engine.
 *
 * UI نباید مستقیماً به Room یا Entityها وابسته باشد.
 * Implementation این قرارداد می‌تواند از Repository اصلی Attribute استفاده کند.
 */
interface AttributeLookupRepository {
    suspend fun load(attributeType: String): Map<String, String>
}
