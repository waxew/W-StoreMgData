/*
================================================

نام فایل:
BusinessRegistry.kt

ماژول:
Business Profile System

وظیفه:
مدیریت پروفایل فعال برنامه.

تمام بخش های برنامه به جای شناخت مستقیم یک فروشگاه،
اطلاعات مورد نیاز خود را از این Registry دریافت می کنند.

با تغییر پروفایل فعال می توان نسخه جدیدی از برنامه ساخت.

================================================
*/

package com.wstoremgdata.businessprofile

/**
 * مرکز دسترسی به کسب و کار فعال.
 *
 * در آینده می تواند از Build Variant یا فایل تنظیمات خارجی
 * مقداردهی شود.
 */
object BusinessRegistry {

    // پروفایل فعلی برنامه
    lateinit var currentProfile: BusinessProfile

    /**
     * ثبت پروفایل فعال برنامه
     */
    fun initialize(profile: BusinessProfile) {
        currentProfile = profile
    }
}
