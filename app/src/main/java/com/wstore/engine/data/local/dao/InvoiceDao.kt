package com.wstore.engine.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import com.wstore.engine.data.local.entity.InvoiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceDao {

    @Query("SELECT * FROM invoices ORDER BY createdAt DESC")
    fun observeInvoices(): Flow<List<InvoiceEntity>>

    @Insert
    suspend fun insert(invoice: InvoiceEntity): Long
}
