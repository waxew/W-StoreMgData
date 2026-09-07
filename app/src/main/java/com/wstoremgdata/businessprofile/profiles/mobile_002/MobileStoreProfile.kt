/*
================================================

نام فایل:
MobileStoreProfile.kt

ماژول:
Business Profile

وظیفه:
تعریف اطلاعات نسخه موبایل فروشی 002

این فایل تنها اطلاعات قابل تغییر کسب و کار را نگهداری می‌کند.
هسته اصلی برنامه نباید به این اطلاعات وابسته باشد.

================================================
*/

package com.wstoremgdata.businessprofile.profiles.mobile_002

import com.wstoremgdata.businessprofile.BusinessProfile

/**
 * پروفایل نسخه موبایل فروشی
 */
val MOBILE_STORE_002 = BusinessProfile(
    id = "mobile_002",
    appName = "Mobile Store Manager",
    businessName = "موبایل فروشی ۲",
    packageName = "com.wstoremgdata.mobile002",
    logo = "mobile_logo",
    appIcon = "mobile_icon",
    primaryColor = "#0066FF",
    businessType = "MOBILE_STORE",
    enabledModules = listOf(
        "product",
        "inventory",
        "sales",
        "customer",
        "reports"
    )
)
