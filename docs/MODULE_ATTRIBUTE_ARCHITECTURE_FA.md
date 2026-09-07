# راهنمای معماری Module و Attribute Schema

## هدف

در این پروژه تفاوت بین قابلیت های نرم افزاری و اطلاعات متغیر کسب و کار باید حفظ شود.

## Module چیست؟

ماژول قابلیتی است که منطق، فرآیند و عملیات دارد.

نمونه:

- Warranty
- Repair
- Appointment
- Advertisement
- Delivery

ماژول ها دارای منطق مستقل هستند.

## Attribute Schema چیست؟

ویژگی های توصیفی هستند که فقط داده نگهداری می کنند.

نمونه:

- Color
- Size
- Storage
- Volume
- Expiration Date

این موارد نباید به Entity های اصلی اضافه شوند.

## قانون معماری

Core نباید بداند کسب و کار چیست.

Business Profile مشخص می کند:

1. چه Module هایی فعال شوند.
2. چه Attribute هایی نمایش داده شوند.

مثال:

mobile_store_001:

Modules:
- Warranty
- Repair

Attributes:
- Storage
- Color

boutique_store_001:

Modules:
- Discount

Attributes:
- Size
- Color
