# راهنمای Runtime موتور Attribute پویا

## هدف

این بخش مسئول مدیریت فیلدهایی است که بر اساس Business Profile تغییر می کنند.

نمونه:

فروشگاه موبایل:
- IMEI
- Storage
- Warranty

فروشگاه پوشاک:
- Size
- Color
- Material

## قانون معماری

Core نباید نام کسب و کار را بشناسد.
تمام تفاوت ها توسط Attribute Schema مشخص می شوند.

## جریان Runtime

Business Profile

-> Attribute Schema

-> Attribute Definition

-> Dynamic Attribute Engine

-> UI Renderer
