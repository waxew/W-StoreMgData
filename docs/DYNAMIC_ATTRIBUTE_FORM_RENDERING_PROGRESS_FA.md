# Dynamic Attribute Form Rendering

## هدف

این بخش نقطه اتصال Attribute Schema به فرم واقعی UI است.

جریان:

Business Profile

↓

Attribute Schema

↓

Attribute Definition

↓

DynamicAttributeForm

↓

Dynamic UI Components

## قوانین معماری

- فرم نباید نوع کسب و کار را بشناسد.
- فیلدها فقط از Metadata ساخته می‌شوند.
- اضافه شدن کسب و کار جدید نباید باعث تغییر UI Core شود.

## نمونه

Mobile Store:
- IMEI
- Warranty
- Storage

Boutique:
- Size
- Color
- Material

هر دو از یک Form Renderer استفاده می‌کنند.
