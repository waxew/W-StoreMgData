/*
================================================
File:
mobile_002.kt

بخش:
Business Profiles

وظیفه:
اطلاعات اختصاصی نسخه موبایل فروشی ۲.

برای ساخت نسخه جدید فقط این فایل تغییر می کند
و هسته اصلی برنامه دست نخورده باقی می ماند.

================================================
*/

package com.wstoremgdata.businessprofile.profiles

import com.wstoremgdata.businessprofile.BusinessProfile

/**
 * پروفایل نمونه برای فروشگاه موبایل
 */
val MOBILE_STORE_002 = BusinessProfile(
    id = "mobile_002",
    appName = "Mobile Store Manager",
    businessName = "موبایل فروشی ۲",
    packageName = "com.mobile.store.two",
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
