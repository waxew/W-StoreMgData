package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.wstore.engine.data.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * دسترسی داده‌ای ماژول کالا.
 *
 * متد getAll برای سازگاری با لایه‌های قبلی حفظ شده است و observeAll برای UI واکنشی استفاده می‌شود.
 * شمارنده‌های تاریخچه نیز قبل از حذف کالا از Repository بررسی می‌شوند تا اسناد Sales/Invoice آسیب نبینند.
 */
@Dao
interface ProductDao {

    @Query("SELECT * FROM products ORDER BY id DESC")
    fun observeAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products ORDER BY id DESC")
    suspend fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ProductEntity?

    @Query(
        "SELECT * FROM products " +
            "WHERE name LIKE '%' || :query || '%' " +
            "OR code LIKE '%' || :query || '%' " +
            "OR category LIKE '%' || :query || '%' " +
            "ORDER BY id DESC"
    )
    suspend fun search(query: String): List<ProductEntity>

    @Query("SELECT COUNT(*) FROM sale_items WHERE productId = :productId")
    suspend fun saleReferenceCount(productId: Long): Int

    @Query("SELECT COUNT(*) FROM invoice_items WHERE productId = :productId")
    suspend fun invoiceReferenceCount(productId: Long): Int

    @Insert
    suspend fun insert(product: ProductEntity): Long

    @Update
    suspend fun update(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity)
}
