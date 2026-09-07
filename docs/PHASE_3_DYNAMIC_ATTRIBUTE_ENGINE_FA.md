# فاز ۳ - Dynamic Attribute Engine

هدف این فاز حذف فیلدهای ثابت وابسته به کسب و کار از Core است.

نمونه:

- موبایل: IMEI، Warranty، Storage
- بوتیک: Size، Color، Material
- آرایشی: Expiration، Volume

این موارد نباید به صورت Class اختصاصی در Core ساخته شوند.

معماری:

Business Profile

↓

Attribute Schema

↓

Attribute Definition

↓

Dynamic UI Renderer

↓

Database

Core فقط نوع Attribute و قوانین آن را می‌شناسد.
