package com.wstore.engine.data.repository

import com.wstore.engine.data.model.AttributeValue
import kotlinx.coroutines.flow.Flow

/**
 * قرارداد دسترسی به Attribute Engine.
 * UI و Profileها نباید به دیتابیس مستقیم متصل شوند.
 */
interface AttributeRepository {
    fun observeValues(ownerId: String, attributeKey: String): Flow<List<AttributeValue>>
}
