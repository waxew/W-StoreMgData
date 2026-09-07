package com.wstore.engine.data.database.entity

/**
 * مدل ذخیره سازی مشتری در دیتابیس داخلی برنامه.
 *
 * نکته معماری:
 * این Entity عمومی است و به هیچ نوع کسب و کار خاصی وابسته نیست.
 * اطلاعات اختصاصی کسب و کارها باید از طریق Business Profile و Attribute Engine اضافه شوند.
 */
data class CustomerRoomEntity(
    val id: Long = 0,
    val fullName: String,
    val phoneNumber: String,
    val email: String?
)
