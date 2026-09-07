# برنامه توسعه رابط کاربری ماژول مشتری

## هدف

ساخت رابط کاربری مستقل برای مدیریت مشتری که در تمام کسب و کارها قابل استفاده باشد.

## صفحات

Customer Module

```
Customer
├── Customer List
├── Add Customer
├── Edit Customer
├── Customer Details
└── Customer History
```

## قوانین

- UI نباید وابسته به نوع کسب و کار باشد.
- اطلاعات اختصاصی از Dynamic Attributes خوانده می‌شود.
- تمام متن‌ها و ظاهر قابل شخصی‌سازی از Business Profile دریافت می‌شوند.

## مرحله بعد

پیاده‌سازی Jetpack Compose UI و اتصال به CustomerViewModel.
