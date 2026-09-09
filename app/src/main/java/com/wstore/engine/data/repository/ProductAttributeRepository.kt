package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.ProductAttributeDao
import com.wstore.engine.data.local.entity.ProductAttributeValueEntity
import com.wstore.engine.profile.ProfileAttributeDefinition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * نام فایل: ProductAttributeRepository.kt
 * ماژول: Dynamic Attribute Data
 * وظیفه: ایجاد مرز بین UI/ViewModel و DAO مقادیر Attribute پویا.
 *
 * این Repository فقط Attributeهای تعریف‌شده در Profile فعال را ذخیره یا پاک می‌کند و
 * Attributeهای ناشناخته یا متعلق به Profileهای دیگر را خودسرانه حذف نمی‌کند.
 */
class ProductAttributeRepository(
    private val dao: ProductAttributeDao
) {

    fun observeValues(productId: Long): Flow<Map<String, String>> =
        dao.observeForProduct(productId).map { rows ->
            rows.associate { row -> row.attributeKey to row.value }
        }

    suspend fun getValues(productId: Long): Map<String, String> =
        dao.getForProduct(productId).associate { row ->
            row.attributeKey to row.value
        }

    /**
     * مقادیر Profile فعال را همگام می‌کند.
     * مقدار خالی به معنی پاک شدن همان Attribute است؛ سایر کلیدهای دیتابیس دست‌نخورده می‌مانند.
     */
    suspend fun saveProfileValues(
        productId: Long,
        values: Map<String, String>,
        definitions: List<ProfileAttributeDefinition>
    ) {
        definitions.forEach { definition ->
            val normalized = values[definition.key].orEmpty().trim()

            if (normalized.isBlank()) {
                dao.deleteValue(
                    productId = productId,
                    attributeKey = definition.key
                )
            } else {
                dao.upsert(
                    ProductAttributeValueEntity(
                        productId = productId,
                        attributeKey = definition.key,
                        value = normalized
                    )
                )
            }
        }
    }
}
