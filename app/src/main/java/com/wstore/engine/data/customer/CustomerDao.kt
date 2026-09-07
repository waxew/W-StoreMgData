package com.wstore.engine.data.customer

/**
 * DAO مشتری
 *
 * این فایل مسئول تعریف عملیات دسترسی به اطلاعات مشتری در دیتابیس است.
 *
 * نکته معماری:
 * این لایه فقط با مفهوم عمومی Customer کار می‌کند و هیچ وابستگی به نوع کسب‌وکار ندارد.
 */
interface CustomerDao {

    // دریافت لیست مشتریان
    suspend fun getCustomers(): List<CustomerEntity>

    // ذخیره مشتری جدید
    suspend fun insertCustomer(customer: CustomerEntity)
}
