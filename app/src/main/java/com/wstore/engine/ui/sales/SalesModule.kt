package com.wstore.engine.ui.sales

/**
 * قرارداد Runtime/Navigation ماژول فروش.
 */
object SalesModule {
    const val ID = "sales"
    const val ROUTE = "sales"

    const val SALE_ID_ARGUMENT = "saleId"
    const val DETAIL_ROUTE = "sales/{saleId}"

    fun detailRoute(saleId: Long): String = "sales/$saleId"
}
