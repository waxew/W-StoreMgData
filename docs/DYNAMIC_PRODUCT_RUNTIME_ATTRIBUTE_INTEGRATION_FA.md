# اتصال Product Runtime به Dynamic Attribute Engine

## هدف

محصول نباید فیلدهای ثابت مخصوص یک کسب و کار داشته باشد.

نمونه‌های مختلف:

- IMEI برای موبایل
- Size برای پوشاک
- Expiration برای محصولات تاریخ‌دار

همگی به صورت Dynamic Attribute ذخیره می‌شوند.

## معماری

Business Profile

↓

Attribute Schema

↓

Attribute Definition

↓

Attribute Value

↓

Product Runtime

## قانون اصلی

Core Product هیچ شناختی از Mobile، Boutique یا Beauty ندارد.
