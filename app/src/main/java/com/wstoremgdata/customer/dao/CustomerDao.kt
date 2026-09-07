/*
================================================

نام فایل:
CustomerDao.kt

ماژول:
مدیریت مشتری

وظیفه:
تعریف دسترسی به اطلاعات مشتریان در دیتابیس.

این فایل فقط مسئول ارتباط با Database است
و نباید شامل منطق تجاری برنامه باشد.

ارتباط:
- CustomerEntity
- CustomerRepository
- Room Database

================================================
*/

package com.wstoremgdata.customer.dao

import com.wstoremgdata.customer.entity.CustomerEntity

/**
 * رابط دسترسی به اطلاعات مشتری.
 *
 * در نسخه نهایی این Interface با Room @Dao تکمیل می‌شود.
 */
interface CustomerDao {

    // دریافت لیست تمام مشتریان
    suspend fun getAllCustomers(): List<CustomerEntity>

    // ذخیره مشتری جدید
    suspend fun insertCustomer(customer: CustomerEntity)

    // حذف مشتری
    suspend fun deleteCustomer(customer: CustomerEntity)
}
