package com.wstore.engine.data.customer

import com.wstore.engine.domain.customer.CustomerRepository
import javax.inject.Inject

class CustomerRepositoryImpl @Inject constructor(
    private val dataSource: CustomerDataSource
) : CustomerRepository {

    override suspend fun getCustomers(): List<CustomerEntity> {
        return dataSource.loadCustomers()
    }

    override suspend fun saveCustomer(customer: CustomerEntity) {
        dataSource.saveCustomer(customer)
    }
}
