package com.wstore.engine.data.model

/**
 * نتیجه حذف کنترل‌شده کالا.
 * کالاهای دارای سابقه Sales یا Invoice حذف فیزیکی نمی‌شوند تا تاریخچه تجاری قابل اتکا بماند.
 */
sealed interface ProductDeleteResult {
    data object Deleted : ProductDeleteResult
    data object NotFound : ProductDeleteResult

    data class BlockedByHistory(
        val saleReferences: Int,
        val invoiceReferences: Int
    ) : ProductDeleteResult
}
