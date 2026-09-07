# وضعیت اتصال Runtime فاز ۲

## هدف

اتصال Business Profile به Runtime برنامه.

## جریان داده

Business Profile JSON

↓

Asset Reader

↓

Parser

↓

BusinessProfile Object

↓

Registry

↓

Feature Resolver

↓

Application Runtime

## قوانین معماری

- Core نباید نام کسب و کارها را بشناسد.
- تنظیمات فقط از Profile خوانده می‌شود.
- اضافه کردن کسب و کار جدید نباید نیازمند تغییر Core باشد.
