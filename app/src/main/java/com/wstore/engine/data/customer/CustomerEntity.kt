package com.wstore.engine.data.customer

/*
====================================================
CustomerEntity.kt

وظیفه:
مدل پایه مشتری در سیستم فروشگاهی.

این مدل برای تمام Business Profile ها مشترک است.

مثال استفاده:
- فروشگاه موبایل
- فروشگاه پوشاک
- فروشگاه آرایشی
- سایر کسب و کارها

اطلاعات اختصاصی هر کسب و کار نباید داخل این Entity قرار گیرد.
====================================================
*/

data class CustomerEntity(
    val id: String,
    val fullName: String,
    val phoneNumber: String,
    val email: String?
)
