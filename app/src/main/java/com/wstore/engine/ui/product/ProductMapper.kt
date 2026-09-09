package com.wstore.engine.ui.product

import com.wstore.engine.data.local.entity.ProductEntity
import com.wstore.engine.data.model.Product

fun ProductEntity.toProduct(): Product {
    return Product(
        id = id,
        name = name,
        code = code,
        category = category,
        price = price,
        stock = stock
    )
}
