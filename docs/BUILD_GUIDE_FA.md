# راهنمای ساخت پروژه W-StoreMgData

## هدف

این پروژه باید در نهایت داخل Android Studio باز شده و خروجی APK تولید کند.

## قوانین معماری

- Core مستقل از نوع کسب و کار است.
- اطلاعات فروشگاه از Business Profile خوانده می‌شود.
- نام هایی مانند mobile یا beauty نباید در Core استفاده شوند.

## خروجی ها

Debug:

- app-debug.apk

Release:

- app-release.apk

## مراحل آینده Build

1. تنظیم Gradle
2. تنظیم Android Plugin
3. اضافه کردن Dependency ها
4. Sync پروژه در Android Studio
5. Build APK

تمام فایل های Kotlin باید دارای توضیحات فارسی باشند.
