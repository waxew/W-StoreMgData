# معماری UI محصول

## هدف

این بخش رابط کاربری محصولات را بدون وابستگی به نوع کسب و کار پیاده می‌کند.

## قانون اصلی

Product Screen نباید بداند برنامه برای چه فروشگاهی ساخته شده است.

نمونه:

موبایل:
- IMEI
- Storage
- Warranty

بوتیک:
- Size
- Color

لوازم آرایشی:
- Volume
- Expiration

همه این موارد توسط Attribute Schema و Business Profile تامین می‌شوند.

## مسیر داده

UI

↓

ViewModel

↓

UseCase

↓

Repository

↓

Data Layer
