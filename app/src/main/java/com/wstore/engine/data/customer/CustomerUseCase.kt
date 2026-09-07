package com.wstore.engine.data.customer

// این فایل منطق عملیاتی مشتری را مدیریت می‌کند.
// UI مستقیماً با Database کار نمی‌کند و از این لایه استفاده می‌کند.

class CustomerUseCase(
    private val repository: CustomerRepository
) {
    fun loadCustomers(): List<CustomerEntity> {
        return repository.getCustomers()
    }

    fun createCustomer(customer: CustomerEntity) {
        repository.saveCustomer(customer)
    }
}
