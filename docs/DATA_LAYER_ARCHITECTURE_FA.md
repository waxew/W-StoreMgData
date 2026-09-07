# معماری Data Layer - راهنمای فارسی

## هدف

این لایه مسئول مدیریت ذخیره سازی اطلاعات است و از Core جدا نگه داشته می شود.

## ساختار

Application

↓

Repository

↓

Data Source

↓

Database / API

## قوانین

1. هیچ نام کسب و کاری نباید داخل Data Layer استفاده شود.

2. اطلاعاتی مانند موبایل، زیبایی، پوشاک و غیره فقط در Business Profile تعریف می شوند.

3. Entity های عمومی توسط Core مدیریت می شوند.

## توسعه آینده

این بخش به Room Database، DAO و API Layer متصل خواهد شد.
