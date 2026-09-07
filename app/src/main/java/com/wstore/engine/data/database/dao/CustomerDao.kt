package com.wstore.engine.data.database.dao

import com.wstore.engine.data.database.entity.CustomerRoomEntity

/**
 * قرارداد دسترسی به اطلاعات مشتری.
 *
 * این فایل محل تعریف عملیات دیتابیس است.
 * پیاده سازی واقعی توسط Room انجام خواهد شد.
 */
interface CustomerDao {

    fun insert(customer: CustomerRoomEntity)

    fun getAll(): List<CustomerRoomEntity>

    fun delete(id: Long)
}
