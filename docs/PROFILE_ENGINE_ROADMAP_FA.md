# نقشه راه موتور پروفایل کسب و کار

## هدف

ایجاد یک موتور White Label که اضافه کردن کسب و کار جدید بدون تغییر Core انجام شود.

## معماری

```
Business Profile Definition
            |
            v
Profile Registry
            |
            v
Schema Loader
            |
            v
Validator
            |
            v
Runtime Engine
```

## قوانین توسعه

- هر کسب و کار فقط یک Definition دارد.
- اطلاعات ثابت داخل Config قرار می‌گیرد.
- UI و Featureها از Profile خوانده می‌شوند.
- اضافه کردن Profile یازدهم نباید باعث تغییر Core شود.

## مراحل پیاده‌سازی

1. Profile Definition
2. Profile Registry
3. Schema Loader
4. Schema Validator
5. Active Profile Resolver
6. Dynamic Attribute Engine
7. UI Profile Engine
