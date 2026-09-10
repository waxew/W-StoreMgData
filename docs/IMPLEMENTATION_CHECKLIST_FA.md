# چک لیست تکمیل معماری W-StoreMgData

## هدف
این فایل مسیر اجرای Refactor را ثبت می‌کند. هدف تبدیل پروژه به یک موتور White Label مبتنی بر Business Profile است.

## قوانین ثابت

- حذف Feature یا کوچک سازی پروژه برای Build ممنوع است.
- تغییرات باید افزایشی و قابل بازگشت باشند.
- Backend و UI باید همیشه مسیر کامل ارتباطی داشته باشند.

## مراحل

- [ ] Profile Registry کامل
- [ ] Schema Loader
- [ ] Schema Validator
- [ ] Active Profile Resolver
- [ ] App Config Runtime
- [ ] Dynamic Attribute Engine
- [ ] UI Profile Engine
- [ ] Theme و Asset Resolver
- [ ] Drawer و App Shell
- [ ] Notification Center
- [ ] Update Manager
- [ ] Ads/VIP Policy
- [ ] Backup/Restore
- [ ] تکمیل ۱۰ Business Profile
- [ ] Build Matrix
- [ ] Runtime Test

## معماری هدف

```text
Business Profile JSON
        ↓
Loader
        ↓
Validator
        ↓
Registry
        ↓
Runtime Engine
        ↓
Backend + UI + Features
```

## توسعه Profile جدید

اضافه کردن Profile یازدهم باید فقط با اضافه کردن Definition و Asset انجام شود و Core نباید تغییر کند.
