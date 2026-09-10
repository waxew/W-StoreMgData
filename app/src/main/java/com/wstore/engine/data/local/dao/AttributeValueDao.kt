package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wstore.engine.data.local.entity.AttributeValueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AttributeValueDao {

    @Query("SELECT * FROM attribute_values WHERE ownerId = :ownerId")
    fun observeByOwner(ownerId: String): Flow<List<AttributeValueEntity>>

    @Query("SELECT * FROM attribute_values WHERE ownerId = :ownerId AND attributeKey = :key")
    fun observeByKey(ownerId: String, key: String): Flow<List<AttributeValueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(value: AttributeValueEntity)
}
