package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.CustomerDao
import com.wstore.engine.data.local.entity.CustomerEntity
import kotlinx.coroutines.flow.Flow

/**
 * نام فایل: CustomerRepository.kt
 * ماژول: Customer Data
 * وظیفه: ایجاد مرز مشخص بین ViewModel و منبع داده Room.
 */
class CustomerRepository(
    private val dao: CustomerDao
) {
    fun observeCustomers(): Flow<List<CustomerEntity>> = dao.observeAll()

    fun searchCustomers(query: String): Flow<List<CustomerEntity>> = dao.search(query)

    fun observeById(id: Long): Flow<CustomerEntity?> = dao.observeById(id)

    suspend fun add(customer: CustomerEntity): Long = dao.insert(customer)

    suspend fun update(customer: CustomerEntity) = dao.update(customer)

    suspend fun delete(customer: CustomerEntity) = dao.delete(customer)

    suspend fun getById(id: Long): CustomerEntity? = dao.getById(id)
}
