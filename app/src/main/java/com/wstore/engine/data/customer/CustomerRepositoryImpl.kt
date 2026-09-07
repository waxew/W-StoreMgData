package com.wstore.engine.data.customer

/**
 * پیاده سازی واقعی Repository مشتری.
 *
 * این کلاس نقطه اتصال بین منطق برنامه و منبع ذخیره اطلاعات است.
 * در این مرحله ساختار اولیه ایجاد شده تا بعد از اتصال Room Database
 * عملیات واقعی ذخیره و بازیابی اطلاعات انجام شود.
 *
 * نکته معماری:
 * این بخش نباید هیچ وابستگی به نوع کسب و کار داشته باشد.
 * اطلاعاتی مثل موبایل، بوتیک یا آرایشی از Business Profile تامین می‌شوند.
 */
class CustomerRepositoryImpl(
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
