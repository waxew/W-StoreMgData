# وضعیت Attribute Validation Engine

## هدف

ایجاد لایه اعتبارسنجی عمومی برای فیلدهای پویا.

## قوانین

Validator نباید هیچ شناختی از نوع کسب و کار داشته باشد.

نمونه:

IMEI، Size، Color و Expiration فقط Attribute هستند.

## مسیر معماری

Business Profile

↓

Attribute Schema

↓

Attribute Definition

↓

Attribute Validator

↓

Dynamic UI
