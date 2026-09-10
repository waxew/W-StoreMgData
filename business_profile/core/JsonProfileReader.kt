package com.wstore.core.profile

/**
 * خواندن تعریف پروفایل از JSON
 *
 * این کلاس فقط مسئول تبدیل داده خارجی به مدل داخلی است.
 * منطق کسب و کار در این لایه قرار نمی گیرد.
 */
class JsonProfileReader {

    fun read(profileJson: String): ProfileDefinition {
        // Parser واقعی JSON در لایه Data قرار می گیرد.
        // این قرارداد برای اتصال Loader به منابع واقعی ایجاد شده است.
        throw UnsupportedOperationException("JSON parser implementation pending")
    }
}
