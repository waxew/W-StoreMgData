# گزارش یکپارچگی Backend و Frontend

## هدف

این سند وضعیت اتصال لایه های داخلی برنامه تا رابط کاربری را مشخص می کند.

## مسیر اصلی داده

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

Database

## وضعیت فعلی

- UI Foundation ایجاد شده است.
- Presentation Layer ایجاد شده است.
- Repository Interface وجود دارد.
- Repository Implementation در حال تکمیل است.
- اتصال نهایی Room Database هنوز باقی مانده است.

## قوانین معماری

- Core نباید نام کسب و کارها را بشناسد.
- Mobile، Beauty، Boutique و سایر موارد فقط Business Profile هستند.
- فیلدهای متغیر محصول از Attribute Schema تامین می شوند.

## مراحل باقی مانده

1. تکمیل Room Database
2. اتصال DAO ها
3. تکمیل Repository Implementation
4. اتصال ViewModel به داده واقعی
5. Build و تست APK
