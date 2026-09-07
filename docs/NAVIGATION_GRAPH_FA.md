# راهنمای Navigation Graph

## هدف

این بخش مسئول مدیریت مسیرهای برنامه اندروید است.

## معماری

```
MainActivity

↓

AppNavigation

↓

NavigationGraph

↓

Screen
```

## قوانین

- مسیرها نباید وابسته به نوع کسب و کار باشند.
- اطلاعات فروشگاه از Business Profile خوانده می‌شود.
- هر قابلیت جدید باید از مسیر Module Engine فعال شود.
