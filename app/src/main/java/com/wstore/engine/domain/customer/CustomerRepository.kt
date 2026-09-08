package com.wstore.engine.domain.customer

interface CustomerRepository {
    fun getCustomers(): List<Any>
}
