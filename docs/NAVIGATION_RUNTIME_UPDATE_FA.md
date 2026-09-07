# بروزرسانی معماری Navigation

## هدف

مدیریت صفحات برنامه بدون وابستگی به نوع کسب و کار.

## جریان

MainActivity

↓

AppNavigation

↓

Screen Route

↓

ViewModel

↓

UseCase

## قوانین

- نام کسب و کار داخل Navigation قرار نمی‌گیرد.
- صفحات عمومی توسط Business Profile محدود یا فعال می‌شوند.
- توسعه آینده بر اساس Module Engine انجام می‌شود.
