package com.wstore.engine.data.repository

import com.wstore.engine.data.local.dao.AttributeValueDao
import com.wstore.engine.data.model.AttributeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation لایه Attribute Engine.
 * UI و Profile ها مستقیماً با Room کار نمی کنند.
 */
class AttributeRepositoryImpl @Inject constructor(
    private val dao: AttributeValueDao
) : AttributeRepository {

    override fun observe(ownerId: String): Flow<List<AttributeValue>> {
        return dao.observeByOwner(ownerId).map { values ->
            values.map {
                AttributeValue(
                    ownerId = it.ownerId,
                    key = it.attributeKey,
                    value = it.value
                )
            }
        }
    }
}
