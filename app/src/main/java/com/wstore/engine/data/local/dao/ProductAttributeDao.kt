package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.wstore.engine.data.local.entity.ProductAttributeValueEntity
import kotlinx.coroutines.flow.Flow

/**
 * نام فایل: ProductAttributeDao.kt
 * ماژول: Dynamic Attribute Data
 * وظیفه: خواندن و ذخیره Attributeهای پویا برای کالاها.
 *
 * این DAO هیچ اطلاعی از موبایل، بوتیک یا سایر صنف‌ها ندارد و فقط با کلید Attribute کار می‌کند.
 */
@Dao
interface ProductAttributeDao {

    @Query(
        "SELECT * FROM product_attribute_values " +
            "WHERE productId = :productId ORDER BY attributeKey ASC"
    )
    fun observeForProduct(productId: Long): Flow<List<ProductAttributeValueEntity>>

    @Query(
        "SELECT * FROM product_attribute_values " +
            "WHERE productId = :productId ORDER BY attributeKey ASC"
    )
    suspend fun getForProduct(productId: Long): List<ProductAttributeValueEntity>

    /**
     * تمام مقادیر واقعی یک Attribute را برای ساخت Lookupهای Profile-Driven برمی‌گرداند.
     * این Query فقط Read است و به نوع کسب‌وکار وابستگی ندارد.
     */
    @Query(
        "SELECT * FROM product_attribute_values " +
            "WHERE attributeKey = :attributeKey AND TRIM(value) != '' " +
            "ORDER BY value COLLATE NOCASE ASC, productId ASC"
    )
    fun observeForAttribute(attributeKey: String): Flow<List<ProductAttributeValueEntity>>

    @Upsert
    suspend fun upsert(value: ProductAttributeValueEntity)

    @Upsert
    suspend fun upsertAll(values: List<ProductAttributeValueEntity>)

    @Query(
        "DELETE FROM product_attribute_values " +
            "WHERE productId = :productId AND attributeKey = :attributeKey"
    )
    suspend fun deleteValue(productId: Long, attributeKey: String)
}
