package com.wstore.engine.domain.customer

import com.wstore.engine.data.customer.CustomerEntity

interface CustomerRepository {
    suspend fun getCustomers(): List<CustomerEntity>
    suspend fun saveCustomer(customer: CustomerEntity)
}
