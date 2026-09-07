/*
================================================

نام فایل:
DeleteCustomerUseCase.kt

ماژول:
مدیریت مشتری

وظیفه:
مدیریت عملیات حذف مشتری از طریق لایه Repository.

توضیح معماری:
این فایل نباید مستقیماً با دیتابیس ارتباط داشته باشد.
تمام عملیات از Repository عبور می‌کند تا معماری قابل توسعه باقی بماند.

================================================
*/

package com.wstoremgdata.customer.usecase

import com.wstoremgdata.customer.repository.CustomerRepository

/**
 * حذف مشتری از سیستم.
 *
 * این کلاس در آینده می‌تواند قوانین حذف،
 * بررسی وابستگی فاکتور یا سوابق خرید را مدیریت کند.
 */
class DeleteCustomerUseCase(
    private val repository: CustomerRepository
) {

    suspend operator fun invoke(customerId: Long) {
        repository.deleteCustomer(customerId)
    }
}
