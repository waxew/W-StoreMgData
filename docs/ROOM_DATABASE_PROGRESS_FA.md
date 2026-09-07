# گزارش پیشرفت Room Database

## وضعیت

لایه دیتابیس از حالت اسکلت اولیه به ساختار Room آماده انتقال داده شد.

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

- دیتابیس نباید نوع کسب و کار را بشناسد.
- فیلدهای اختصاصی مانند IMEI، Size و Color از Attribute Engine تامین می‌شوند.
- Entity های عمومی در Data Layer نگهداری می‌شوند.

## مراحل بعد

- ساخت Database Provider
- اتصال Dependency Injection
- تست ذخیره و بازیابی مشتری
