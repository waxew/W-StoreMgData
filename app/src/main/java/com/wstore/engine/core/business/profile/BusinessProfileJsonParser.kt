package com.wstore.engine.core.business.profile

/**
 * پارسر پروفایل کسب و کار.
 *
 * وظیفه این کلاس تبدیل ساختار JSON پروفایل کسب و کار
 * به مدل Runtime است.
 *
 * این لایه نباید هیچ شناختی از نوع کسب و کار داشته باشد.
 * مانند موبایل، بوتیک یا آرایشی.
 */
class BusinessProfileJsonParser {

    fun parse(json: String): BusinessProfile {
        // پیاده سازی نهایی JSON Parser در اتصال Serialization تکمیل می شود.
        // این متد نقطه ورود استاندارد Runtime خواهد بود.
        return BusinessProfile(
            id = "",
            name = "",
            packageName = "",
            modules = emptyList(),
            features = emptyList()
        )
    }
}
