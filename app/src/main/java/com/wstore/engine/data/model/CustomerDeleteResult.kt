package com.wstore.engine.data.model

/**
 * نتیجه حذف مشتری با رعایت یکپارچگی سوابق فروش.
 */
sealed interface CustomerDeleteResult {
    data object Deleted : CustomerDeleteResult
    data object NotFound : CustomerDeleteResult
    data class BlockedBySalesHistory(
        val saleReferences: Int
    ) : CustomerDeleteResult
}
