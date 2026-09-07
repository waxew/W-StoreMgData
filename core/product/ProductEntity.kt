/*
================================================
نام فایل:
ProductEntity.kt

وظیفه:
تعریف موجودیت پایه کالا در هسته فروشگاهی.

نکته مهم:
این فایل نباید شامل اطلاعات اختصاصی کسب و کار باشد.
مواردی مانند IMEI، Size، Color و Expiration از طریق Module و Attribute Engine مدیریت می‌شوند.

================================================
*/

package core.product

/**
 * موجودیت عمومی کالا.
 * مستقل از نوع فروشگاه.
 */
data class ProductEntity(
    val id: String,
    val name: String,
    val categoryId: String?,
    val brandId: String?,
    val purchasePrice: Double,
    val salePrice: Double,
    val image: String?
)
