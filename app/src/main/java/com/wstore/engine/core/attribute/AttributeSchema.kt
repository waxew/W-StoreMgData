package com.wstore.engine.core.attribute

/**
 * Schema مجموعه ای از Attribute های مورد نیاز یک موجودیت است.
 *
 * این کلاس وابسته به نوع کسب و کار نیست.
 * Business Profile تعیین می کند چه Schema ای فعال شود.
 */
data class AttributeSchema(
    val id: String,
    val name: String,
    val attributes: List<AttributeDefinition>
)
