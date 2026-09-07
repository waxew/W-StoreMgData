package com.wstore.engine.core.attribute

/**
 * Loader layer for dynamic attribute schemas.
 *
 * This class is responsible for converting external schema sources
 * into AttributeSchema objects.
 *
 * Business types are not handled here.
 * Mobile, boutique and beauty are only configuration profiles.
 */
class AttributeSchemaLoader {

    fun load(schema: AttributeSchema): AttributeSchema {
        return schema
    }
}
