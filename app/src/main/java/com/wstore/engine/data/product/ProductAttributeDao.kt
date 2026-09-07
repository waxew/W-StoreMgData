package com.wstore.engine.data.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductAttributeDao {

    @Insert
    suspend fun insert(attribute: ProductAttributeEntity)

    @Query("SELECT * FROM product_attributes WHERE productId = :productId")
    suspend fun getByProductId(productId: Long): List<ProductAttributeEntity>
}
