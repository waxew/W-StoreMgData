# معماری Business Profile

## جریان تصمیم‌گیری

Application

↓

BusinessRegistry

↓

BusinessProfile

↓

Modules + Attribute Schema

## نمونه تصمیم در محصول

Product Core عمومی است.

اگر Profile دارای Module با شناسه imei باشد، بخش IMEI فعال می‌شود.

اگر Profile دارای Attribute با شناسه size باشد، فیلد سایز نمایش داده می‌شود.

## اصول

- Core عمومی باقی می‌ماند.
- Module ها منطق کسب‌وکار را اضافه می‌کنند.
- Attribute Schema داده‌های متغیر را تعریف می‌کند.
- تمام فایل‌های Kotlin باید توضیح فارسی داشته باشند.
