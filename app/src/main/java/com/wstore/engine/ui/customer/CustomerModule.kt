package com.wstore.engine.ui.customer

/**
 * قرارداد Runtime/Navigation ماژول مشتری.
 */
object CustomerModule {
    const val ROUTE = "customer"
    const val ID = "customer"

    const val CUSTOMER_ID_ARGUMENT = "customerId"
    const val DETAIL_ROUTE = "customer/{customerId}"

    fun detailRoute(customerId: Long): String = "customer/$customerId"
}
