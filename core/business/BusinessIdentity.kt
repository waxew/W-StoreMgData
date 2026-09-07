package core.business

/*
================================================
نام فایل:
BusinessIdentity.kt

وظیفه:
تعریف اطلاعات هویتی کسب‌وکار در معماری Business Profile.

این فایل فقط تنظیمات هویتی را نگهداری می‌کند و نباید شامل منطق کسب‌وکار باشد.

مواردی مانند نام برنامه، لوگو و اطلاعات برند از این بخش خوانده می‌شوند.

================================================
*/

data class BusinessIdentity(
    val id: String,
    val applicationName: String,
    val businessName: String,
    val packageName: String,
    val logoPath: String?,
    val iconPath: String?
)
