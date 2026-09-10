package com.wstore.business_profile.schema

/**
 * لایه پایه ذخیره و بازیابی Attribute های پویا.
 *
 * این Interface باعث می‌شود ماژول‌های اصلی مانند Product بدون وابستگی
 * به نوع کسب و کار از فیلدهای اختصاصی استفاده کنند.
 */
interface AttributeValueRepository {

    fun save(values: List<AttributeValue>)

    fun getByOwnerId(ownerId: String): List<AttributeValue>
}
