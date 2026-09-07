/*
================================================

نام فایل:
CustomerEntity.kt

ماژول:
Customer Management

وظیفه:
مدل اصلی اطلاعات مشتری در سیستم.

این Entity عمومی است و برای تمام کسب و کارها
استفاده می‌شود.

مثال:
- فروشگاه موبایل
- بوتیک
- سالن زیبایی
- فروشگاه لوازم آرایشی

اطلاعات اختصاصی هر کسب و کار از طریق
Custom Attributes اضافه خواهد شد.

================================================
*/

package com.wstoremgdata.customer.entity

import com.wstoremgdata.core.entity.BaseEntity

/**
 * اطلاعات پایه مشتری.
 *
 * این کلاس فقط اطلاعات مشترک مشتری را نگهداری می‌کند
 * و منطق تجاری داخل آن قرار نمی‌گیرد.
 */

data class CustomerEntity(

    val firstName: String,

    val lastName: String,

    val phoneNumber: String,

    val email: String? = null,

    val address: String? = null,

    val notes: String? = null

) : BaseEntity()
