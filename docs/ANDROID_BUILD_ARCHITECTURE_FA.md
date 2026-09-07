# راهنمای معماری Android Build پروژه W-StoreMgData

## هدف

این پروژه باید در نهایت به یک برنامه واقعی Android با خروجی APK تبدیل شود.

## قوانین

- Core از Android UI مستقل نگهداری می‌شود.
- Business Profile نباید داخل Package های کسب‌وکاری باشد.
- نام‌هایی مانند mobile یا beauty نباید در ساختار Core استفاده شوند.

## ساختار نهایی مورد انتظار

```
app
core
business_profile
modules
ui
data
```

## خروجی‌ها

- Debug APK برای تست
- Release APK برای انتشار

## معماری پیشنهادی

Kotlin + Jetpack Compose + MVVM + Clean Architecture
