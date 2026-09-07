# برنامه پیاده سازی Room Database

## هدف

اتصال لایه های برنامه به ذخیره سازی واقعی اطلاعات در Android.

## معماری

UI

↓

ViewModel

↓

UseCase

↓

Repository

↓

DataSource

↓

Room Database

## قوانین

- دیتابیس نباید وابسته به نوع کسب و کار باشد.
- اطلاعاتی مثل IMEI، Size و Color باید از Attribute Engine مدیریت شوند.
- Entity های عمومی مثل Customer و Product در Core باقی می مانند.

## مراحل بعدی

- ساخت Room Entity
- ساخت DAO واقعی
- اتصال AppDatabase
- تست ذخیره و بازیابی داده
