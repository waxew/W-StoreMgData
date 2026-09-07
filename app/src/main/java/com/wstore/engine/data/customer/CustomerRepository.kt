package com.wstore.engine.data.customer

// این فایل قرارداد دسترسی به اطلاعات مشتری را تعریف می‌کند.
// Repository بین منطق برنامه و منبع داده قرار می‌گیرد.
// این بخش مستقل از نوع کسب‌وکار است و برای تمام نسخه‌های فروشگاهی استفاده می‌شود.

interface CustomerRepository {
    fun getCustomers(): List<CustomerEntity>
    fun saveCustomer(customer: CustomerEntity)
}
