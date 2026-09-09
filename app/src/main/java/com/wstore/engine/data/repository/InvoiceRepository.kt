package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.InvoiceDao
import com.wstore.engine.data.local.entity.InvoiceEntity
import com.wstore.engine.data.local.entity.InvoiceItemEntity
import com.wstore.engine.data.local.entity.SaleEntity
import kotlinx.coroutines.flow.Flow

/**
 * مرز داده‌ای ماژول Invoice.
 */
class InvoiceRepository(
    private val dao: InvoiceDao
) {
    fun observeInvoices(): Flow<List<InvoiceEntity>> = dao.observeInvoices()

    fun observeSalesWithoutInvoice(): Flow<List<SaleEntity>> =
        dao.observeSalesWithoutInvoice()

    fun observeInvoiceItems(invoiceId: Long): Flow<List<InvoiceItemEntity>> =
        dao.observeInvoiceItems(invoiceId)

    suspend fun createFromSale(saleId: Long): Long = dao.createFromSale(saleId)

    suspend fun getInvoice(invoiceId: Long): InvoiceEntity? = dao.getInvoice(invoiceId)
}
