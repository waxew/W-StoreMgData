# راهنمای App Config مرکزی

## هدف

فایل `config/app_config.json` منبع واحد اطلاعات پایه برنامه است. هدف این است که برای ساخت White Label جدید، نام برنامه، شناسه پکیج، نسخه و تنظیمات عمومی در فایل‌های مختلف تکرار نشوند.

## بخش app

- `name`: نام نمایشی برنامه
- `applicationId`: شناسه پکیج خروجی
- `versionCode`: شماره داخلی نسخه
- `versionName`: نسخه قابل نمایش
- `iconKey`: کلید Asset آیکون
- `logoKey`: کلید Asset لوگو

`app/build.gradle.kts` این مقادیر را در زمان Build می‌خواند و `applicationId`، نسخه و `@string/app_name` را تولید می‌کند.

## بخش company

اطلاعات مالک/توسعه‌دهنده برنامه در این بخش نگهداری می‌شود:

- نام AS Team
- نام نمایشی
- کلید لوگو
- ایمیل پشتیبانی

Screenهای درباره نرم‌افزار و تماس با ما باید این اطلاعات را از Runtime Config دریافت کنند و مقدارها را Hard Code نکنند.

## بخش startup

تنظیمات Logo Motion:

- روشن/خاموش
- مدت نمایش
- نام Animation Variant

## بخش ads

این قسمت فقط Policy تبلیغات را تعریف می‌کند و SDK تبلیغاتی را به Core متصل نمی‌کند.

- `enabled`
- `showForGuest`
- `showForExpiredSubscription`
- `hideForVip`
- `provider`
- Banner/Interstitial flags

منطق فعلی در `AdsPolicyResolver` متمرکز است.

## بخش update

- فعال بودن Update Checker
- بررسی در Startup
- آدرس Manifest نسخه
- Popup
- Notification Badge

اگر `manifestUrl` خالی باشد، سرویس شبکه نباید Update Check واقعی را شروع کند.

## بخش backup

مشخص می‌کند Backup شامل چه بخش‌هایی باشد:

- Database
- Preferences
- User Files

## بخش ui

تنظیمات App Shell مشترک:

- Drawer
- Top Bar
- حالت نمایش عنوان وسط Top Bar
- سیاست Back از Root Feature به Home

## اصل تغییر

برای ساخت برنامه مستقل جدید ابتدا `app_config.json` را تغییر دهید و سپس فقط یک Business Profile را `enabled=true` کنید. Core نباید برای تغییر نام یا نوع کسب‌وکار اصلاح شود.
