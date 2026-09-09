package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.wstore.engine.data.local.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    suspend fun getAll(): List<ProductEntity>

    @Insert
    suspend fun insert(product: ProductEntity)
}
