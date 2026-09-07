/*
================================================

نام فایل:
CustomerRepository.kt

ماژول:
مدیریت مشتری

وظیفه:
لایه میانی بین دیتابیس و منطق برنامه.

Repository باعث می‌شود UI و ViewModel
به صورت مستقیم با Database ارتباط نداشته باشند.

ارتباط:
- CustomerDao
- Customer UseCase
- Customer ViewModel

================================================
*/

package com.wstoremgdata.customer.repository

import com.wstoremgdata.customer.dao.CustomerDao
import com.wstoremgdata.customer.entity.CustomerEntity

/**
 * مدیریت عملیات مشتری.
 */
class CustomerRepository(
    private val customerDao: CustomerDao
) {

    // دریافت مشتریان از منبع داده
    suspend fun getCustomers(): List<CustomerEntity> {
        return customerDao.getAllCustomers()
    }

    // ثبت مشتری جدید
    suspend fun addCustomer(customer: CustomerEntity) {
        customerDao.insertCustomer(customer)
    }

    // حذف مشتری
    suspend fun removeCustomer(customer: CustomerEntity) {
        customerDao.deleteCustomer(customer)
    }
}
