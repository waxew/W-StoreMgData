/*
================================================
File:
BusinessProfile.kt

بخش:
Business Profile Engine

وظیفه:
مدل اصلی اطلاعات قابل تغییر هر کسب و کار.

این فایل مشخص می کند هر نسخه از برنامه
چه نام، برند، آیکون، رنگ و ماژول هایی داشته باشد.

هسته برنامه نباید اطلاعات یک کسب و کار خاص را
مستقیماً داخل کد خود داشته باشد.

================================================
*/

package com.wstoremgdata.businessprofile

/**
 * اطلاعات پایه یک نسخه از نرم افزار فروشگاهی
 */
data class BusinessProfile(
    // شناسه یکتا برای هر کسب و کار
    val id: String,

    // نامی که کاربر در برنامه می بیند
    val appName: String,

    // نام واقعی فروشگاه
    val businessName: String,

    // شناسه پکیج برنامه
    val packageName: String,

    // فایل لوگو
    val logo: String,

    // آیکون برنامه
    val appIcon: String,

    // رنگ اصلی رابط کاربری
    val primaryColor: String,

    // نوع کسب و کار
    val businessType: String,

    // ماژول های فعال برنامه
    val enabledModules: List<String>
)
