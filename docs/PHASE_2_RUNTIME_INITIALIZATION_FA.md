# راهنمای اتصال Runtime در فاز ۲

## هدف

Business Profile بعد از بارگذاری باید به Runtime برنامه متصل شود.

جریان:

Business Profile JSON

↓

Profile Loader

↓

Parser

↓

BusinessProfile Object

↓

Runtime Initializer

↓

Module Registry

## قوانین معماری

- Core نباید نام کسب و کارها را بشناسد.
- mobile، boutique و beauty فقط داده هستند.
- فعال شدن قابلیت‌ها از طریق Profile انجام می‌شود.
- ماژول‌ها مستقل از نوع فروشگاه باقی می‌مانند.
