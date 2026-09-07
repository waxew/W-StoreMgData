package com.wstore.engine.core.product

import com.wstore.engine.core.attribute.AttributeValue

/**
 * اتصال مقدارهای Dynamic Attribute به Product Runtime.
 *
 * Product Core فقط Attribute Value را نگهداری می‌کند و
 * هیچ شناختی از نوع کسب و کار ندارد.
 */
data class ProductAttributeValue(
    val productId: String,
    val attribute: AttributeValue
)
