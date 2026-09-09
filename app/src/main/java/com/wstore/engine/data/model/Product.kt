package com.wstore.engine.data.model

data class Product(
    val id: Long = 0,
    val name: String,
    val code: String,
    val category: String,
    val price: Double,
    val stock: Int
)
