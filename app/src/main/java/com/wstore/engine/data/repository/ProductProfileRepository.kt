package com.wstore.engine.data.repository

import androidx.room.withTransaction
import com.wstore.engine.data.local.dao.ProductDao
import com.wstore.engine.data.local.database.AppDatabase
import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.profile.ProfileAttributeDefinition

/**
 * نام فایل: ProductProfileRepository.kt
 * ماژول: Product / Dynamic Attribute Data
 * وظیفه: ذخیره اتمیک اطلاعات پایه Product و Attributeهای Business Profile.
 *
 * هدف این Repository جلوگیری از وضعیت نیمه‌کاره است؛ اگر ذخیره Attributeها شکست بخورد،
 * ثبت/ویرایش Product نیز در همان Transaction دیتابیس Rollback می‌شود.
 */
class ProductProfileRepository(
    private val database: AppDatabase,
    private val productDao: ProductDao,
    private val attributeRepository: ProductAttributeRepository
) {

    suspend fun add(
        product: ProductEntity,
        attributes: Map<String, String>,
        definitions: List<ProfileAttributeDefinition>
    ): Long = database.withTransaction {
        val productId = productDao.insert(product)
        attributeRepository.saveProfileValues(
            productId = productId,
            values = attributes,
            definitions = definitions
        )
        productId
    }

    suspend fun update(
        product: ProductEntity,
        attributes: Map<String, String>,
        definitions: List<ProfileAttributeDefinition>
    ) {
        database.withTransaction {
            productDao.update(product)
            attributeRepository.saveProfileValues(
                productId = product.id,
                values = attributes,
                definitions = definitions
            )
        }
    }
}
