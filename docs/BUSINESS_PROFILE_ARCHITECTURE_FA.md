# معماری Business Profile

## هدف

Business Profile باعث می شود یک هسته نرم افزاری مشترک برای کسب و کارهای مختلف استفاده شود.

## ساختار

Business Profile شامل:

- شناسه کسب و کار
- نام نمایشی
- پکیج خروجی
- ماژول های فعال
- قابلیت های فعال

## قانون مهم

Core نباید نام کسب و کارها را بشناسد.

نمونه اشتباه:

mobile_store
beauty_store

داخل کد اصلی نباید وجود داشته باشد.

نمونه صحیح:

Product Module
Inventory Module
Attribute Module

و فعال سازی توسط Profile انجام می شود.

## جریان Runtime

Business Profile

↓

Profile Loader

↓

Registry

↓

Feature Resolver

↓

UI Runtime

## مثال

فروشگاه موبایل:

IMEI و Warranty توسط Attribute Schema فعال می شوند.

بوتیک:

Size و Color فعال می شوند.

هسته برنامه تغییر نمی کند.
