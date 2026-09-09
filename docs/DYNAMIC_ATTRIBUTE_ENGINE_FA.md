# راهنمای Dynamic Attribute Engine

## هدف

هدف این لایه این است که Product برای هر صنف Entity جداگانه نداشته باشد. فیلدهای اختصاصی مانند IMEI، سایز، تاریخ انقضا، OEM، ISBN و وزن جواهر از Business Profile تعریف می‌شوند و بدون تغییر ستون‌های اصلی Product قابل ذخیره هستند.

## معماری

```text
Business Profile JSON
        ↓
ProfileLoader / Validator
        ↓
ProfileAttributeDefinition
        ↓
DynamicAttributeFields (Compose)
        ↓
ProductViewModel
        ↓
ProductProfileRepository
        ↓
Room Transaction
   ┌────┴─────────┐
ProductDao   ProductAttributeDao
```

## جدول دیتابیس

از نسخه 6 دیتابیس جدول زیر اضافه شده است:

```text
product_attribute_values
```

کلید اصلی ترکیبی:

```text
productId + attributeKey
```

ساختار مقدار:

- `productId`
- `attributeKey`
- `value`
- `updatedAt`

مقدار به صورت String ذخیره می‌شود و Type واقعی از Schema Profile مشخص می‌شود. این روش اجازه می‌دهد Attribute جدید بدون Migration دیتابیس اضافه شود.

## حفظ داده‌های قبلی

Migration نسخه 5 به 6 فقط جدول جدید را اضافه می‌کند. هیچ جدول یا ستون قدیمی حذف، Rename یا بازسازی نمی‌شود.

Productهای موجود بدون Attribute نیز معتبر باقی می‌مانند.

## ذخیره اتمیک

`ProductProfileRepository` Product پایه و Attributeهای Profile را داخل یک `Room.withTransaction` ذخیره می‌کند. بنابراین اگر ذخیره Attribute شکست بخورد، ثبت/ویرایش Product نیز Rollback می‌شود.

## Attributeهای Profileهای دیگر

در زمان ویرایش، Repository فقط کلیدهای Schema Profile فعال را تغییر می‌دهد. Attributeهای ناشناخته به صورت خودکار حذف نمی‌شوند. این تصمیم برای جلوگیری از حذف ناخواسته داده‌ها گرفته شده است.

## Renderer UI

`DynamicAttributeFields.kt` در حال حاضر Typeهای زیر را پشتیبانی می‌کند:

- text
- number
- decimal
- date
- barcode
- serial
- boolean
- option

نوع `option` به صورت انتخاب تک‌گزینه‌ای نمایش داده می‌شود و `boolean` با Checkbox.

## اضافه کردن Attribute جدید

اگر Type آن از Typeهای موجود باشد:

1. فقط Attribute را به JSON Profile اضافه کنید.
2. Build را اجرا کنید.
3. فیلد در فرم Product ظاهر می‌شود.
4. مقدار بدون Migration جدید در `product_attribute_values` ذخیره می‌شود.

اگر Type UI کاملاً جدیدی لازم باشد، ابتدا Renderer عمومی آن Type به Dynamic Attribute Engine اضافه می‌شود؛ سپس تمام Profileها می‌توانند از آن استفاده کنند.
