package com.wstore.engine.data.customer

import javax.inject.Inject

/**
 * پیاده سازی واقعی Repository مشتری.
 *
 * این کلاس نقطه اتصال بین منطق برنامه و منبع ذخیره اطلاعات است.
 *
 * در فاز ۱ وابستگی‌ها توسط Hilt تزریق می‌شوند تا لایه‌ها مستقل باقی بمانند.
 *
 * این بخش هیچ شناختی از نوع کسب و کار ندارد.
 * اطلاعاتی مثل موبایل، بوتیک یا آرایشی فقط از Business Profile تامین می‌شوند.
 */
class CustomerRepositoryImpl @Inject constructor(
    private val dataSource: CustomerDataSource
) : CustomerRepository {

    override suspend fun getCustomers(): List<CustomerEntity> {
        // دریافت اطلاعات مشتریان از DataSource
        return dataSource.getCustomers()
    }

    override suspend fun saveCustomer(customer: CustomerEntity) {
        // ذخیره مشتری از طریق DataSource
        dataSource.saveCustomer(customer)
    }
}
