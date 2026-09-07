# معماری Product Attribute Resolver

## هدف

این بخش وظیفه اتصال محصول به ویژگی های پویا را دارد.

هسته محصول نباید برای هر کسب و کار تغییر کند.

مثال:

فروشگاه موبایل:

- IMEI
- Warranty
- Storage

بوتیک:

- Size
- Color
- Material

لوازم آرایشی:

- Volume
- Expiration

این موارد Attribute هستند و از Business Profile و Attribute Schema تامین می‌شوند.

## جریان داده

Business Profile

↓

Attribute Schema

↓

Product Attribute Resolver

↓

Product UI

## قانون معماری

هیچ نام کسب و کار نباید در Core Product قرار گیرد.
