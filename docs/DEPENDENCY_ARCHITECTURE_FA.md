# معماری Dependency در W-StoreMgData

## هدف

مدیریت وابستگی‌های برنامه بدون ایجاد اتصال مستقیم بین بخش‌ها.

## ساختار

Application

↓

Dependency Container

↓

Core Services

↓

Modules

## قوانین

- Core نباید به نوع کسب‌وکار وابسته باشد.
- نام‌هایی مانند mobile یا beauty نباید در Package های Core استفاده شوند.
- Business Profile فقط تنظیمات و قابلیت‌های فعال را مشخص می‌کند.

## آینده

این لایه قابلیت جایگزینی با Dependency Injection Framework مانند Hilt را دارد.
