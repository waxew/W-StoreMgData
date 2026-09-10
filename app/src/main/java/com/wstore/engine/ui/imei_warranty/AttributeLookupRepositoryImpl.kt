package com.wstore.engine.ui.imei_warranty

import javax.inject.Inject

/**
 * Implementation layer between Dynamic Attribute UI and storage engine.
 *
 * The first version keeps the integration boundary isolated so Product Core
 * and Dynamic Attribute Core remain unchanged.
 */
class AttributeLookupRepositoryImpl @Inject constructor(
    // Repository dependencies will be injected here after binding the existing
    // Product Attribute storage layer.
) : AttributeLookupRepository {

    override suspend fun load(attributeType: String): Map<String, String> {
        // No fake business data is returned.
        // Real ProductAttribute storage binding is the next integration step.
        return emptyMap()
    }
}
