package com.wstore.engine.core.business.profile

/**
 * خواندن فایل های Business Profile از منابع برنامه.
 *
 * این لایه فقط مسئول دسترسی به تنظیمات کسب و کار است.
 * هیچ وابستگی به نوع کسب و کار (Mobile، Beauty و ...) ندارد.
 */
class BusinessProfileAssetReader {

    /**
     * در نسخه فعلی Foundation ایجاد شده است.
     * اتصال واقعی AssetManager در مرحله Runtime Integration انجام می‌شود.
     */
    fun readProfile(fileName: String): String {
        return fileName
    }
}
