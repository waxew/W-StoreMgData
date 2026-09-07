# راهنمای Business Profile

## هدف

Business Profile لایه تعریف هویت و قابلیت‌های هر نسخه از سیستم فروشگاهی است.

Core Engine نباید اطلاعات یک کسب‌وکار خاص را داخل خود نگهداری کند.

هر نسخه فروشگاهی فقط با تغییر Profile ساخته می‌شود.

## ساختار

Business Profile سه بخش اصلی دارد:

1. Identity

اطلاعات ظاهری و برند:
- نام برنامه
- نام فروشگاه
- شناسه
- لوگو
- آیکون
- رنگ‌ها

2. Module Configuration

فعال‌سازی قابلیت‌های دارای منطق:
- IMEI
- Warranty
- Repair
- Reservation

3. Attribute Schema

فیلدهای متغیر اطلاعاتی:
- Size
- Color
- Volume
- Expiration Date

## تفاوت Module و Attribute

Module دارای منطق و فرآیند است.

مثال:
IMEI، گارانتی و تعمیرات.

Attribute فقط داده توصیفی است.

مثال:
رنگ، سایز و جنس.

## قوانین توسعه

- هیچ اطلاعات کسب‌وکار داخل Core نوشته نشود.
- هر فایل Profile دارای کامنت فارسی باشد.
- قابلیت جدید ابتدا باید مشخص کند Core است یا Module.
