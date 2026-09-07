# جریان اجرای Business Runtime

## هدف

این سند توضیح می‌دهد برنامه چگونه بدون وابستگی به نوع کسب و کار اجرا می‌شود.

## جریان اصلی

Application Start

↓

WStoreApplication

↓

ApplicationRuntime

↓

BusinessProfileLoader

↓

RuntimeModuleLoader

↓

Core Engine

## قانون معماری

نام کسب و کار مانند موبایل، بوتیک یا آرایشی نباید در Core استفاده شود.

این موارد فقط در Business Profile تعریف می‌شوند.

Core فقط Module و Schema فعال را دریافت می‌کند.
