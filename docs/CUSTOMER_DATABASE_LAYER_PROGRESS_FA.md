# وضعیت لایه دیتابیس مشتری

## هدف

اتصال کامل مسیر مشتری از رابط کاربری تا ذخیره سازی دائمی.

مسیر معماری:

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

DAO

↓

Database

## قوانین

- Core نباید به نوع کسب و کار وابسته باشد.
- فیلدهای اختصاصی مانند IMEI یا Size در Customer قرار نمی گیرند.
- ویژگی های اختصاصی توسط Business Profile و Attribute Engine مدیریت می شوند.
