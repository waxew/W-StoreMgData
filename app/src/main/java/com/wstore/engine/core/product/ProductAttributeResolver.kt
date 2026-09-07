package com.wstore.engine.core.product

/**
 * مسئولیت این کلاس:
 *
 * اتصال محصول به Attribute های پویا بر اساس تنظیمات کسب و کار.
 *
 * نکته معماری:
 * هسته برنامه نباید بداند محصول مربوط به موبایل، بوتیک یا هر کسب و کار دیگری است.
 * فیلدهایی مانند IMEI، سایز، رنگ یا تاریخ انقضا از Business Profile و Schema دریافت می‌شوند.
 */
class ProductAttributeResolver {

    /**
     * در مراحل بعد این متد از Attribute Schema داده دریافت می‌کند.
     */
    fun resolve(productType: String): List<String> {
        // فعلاً فقط قرارداد اولیه ایجاد شده است.
        // منطق واقعی پس از اتصال Business Profile تکمیل می‌شود.
        return emptyList()
    }
}
