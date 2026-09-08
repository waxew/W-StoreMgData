package com.wstore.engine.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.wstore.engine.data.database.entity.CustomerRoomEntity

/**
 * DAO واقعی Room برای مدیریت اطلاعات مشتری.
 */
@Dao
interface CustomerDao {

    @Insert
    suspend fun insert(customer: CustomerRoomEntity)

    @Query("SELECT * FROM customers")
    suspend fun getAll(): List<CustomerRoomEntity>

    @Delete
    suspend fun delete(customer: CustomerRoomEntity)
}
