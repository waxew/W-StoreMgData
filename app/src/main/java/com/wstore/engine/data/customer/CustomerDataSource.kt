package com.wstore.engine.data.customer

/**
 * منبع داده مشتری
 *
 * این کلاس مرز بین Repository و منبع ذخیره‌سازی اطلاعات است.
 * در آینده می‌تواند به Room، API یا Cloud متصل شود.
 */
class CustomerDataSource(
    private val dao: CustomerDao
) {

    // دریافت مشتریان از منبع داده
    suspend fun loadCustomers(): List<CustomerEntity> {
        return dao.getCustomers()
    }

    // ذخیره مشتری در منبع داده
    suspend fun saveCustomer(customer: CustomerEntity) {
        dao.insertCustomer(customer)
    }
}
