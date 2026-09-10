# معماری Profile Registry + Schema Loader

## هدف

این لایه هسته مدیریت پروفایل‌های کسب و کار است. اضافه کردن پروفایل جدید نباید نیازمند تغییر در Core باشد.

## معماری

```
Business Profile Definition
          |
          v
Profile Registry
          |
          v
Schema Loader
          |
          v
Runtime Engine
          |
          +--> UI
          +--> Features
          +--> Attributes
```

## قوانین

- هر کسب و کار فقط یک Definition دارد.
- Core نباید نام کسب و کارها را Hard Code کند.
- اضافه شدن پروفایل جدید فقط با اضافه شدن یک فایل Definition انجام می‌شود.
- فعال بودن پروفایل با Flag کنترل می‌شود.

## ساختار پیشنهادی پروفایل

```json
{
  "id": "mobile_store_001",
  "enabled": true,
  "name": "فروشگاه موبایل",
  "modules": [],
  "features": [],
  "attributes": [],
  "uiProfile": "mobile_default"
}
```

## توسعه آینده

پروفایل یازدهم باید فقط یک Definition جدید اضافه کند و نباید باعث تغییر در Core Engine شود.
