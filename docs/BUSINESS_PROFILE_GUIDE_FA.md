# راهنمای Business Profile

## هدف

Business Profile مشخص می‌کند یک Build برای چه کسب‌وکاری است. انتخاب نوع کسب‌وکار توسط توسعه‌دهنده انجام می‌شود، نه کاربر نهایی.

در هر Build باید دقیقاً یک فایل Profile دارای:

```json
"enabled": true
```

باشد.

اگر هیچ Profile فعال نباشد یا بیش از یک Profile فعال باشد، `ActiveProfileResolver` Runtime را متوقف می‌کند تا خروجی اشتباه ساخته نشود.

## محل Profileها

```text
config/business_profiles/
```

Loader نام فایل‌ها را Hard Code نمی‌کند. هر فایل JSON جدید به‌صورت خودکار کشف می‌شود، به شرط آنکه نام آن با `schema_` شروع نشود و Schema معتبر داشته باشد.

## ساختار Profile

```text
schemaVersion
id
enabled
identity
modules
features
attributes
uiProfile
theme
assets
```

### identity

نام کسب‌وکار و `businessType` را تعریف می‌کند.

### modules

هر Module با Boolean فعال یا غیرفعال می‌شود.

مثال:

```json
"modules": {
  "product": true,
  "inventory": true,
  "sales": true,
  "imei": false
}
```

### features

Feature Flagهای صنفی را نگهداری می‌کند. فقط Featureهایی که `true` هستند وارد `ProfileDefinition.features` می‌شوند.

### attributes

فیلدهای Dynamic مخصوص Product یا Domainهای دیگر را تعریف می‌کند.

نوع‌های فعلی Schema:

- `text`
- `number`
- `decimal`
- `date`
- `boolean`
- `option`
- `barcode`
- `serial`

برای `option` باید حداقل یک گزینه وجود داشته باشد.

### uiProfile

Metadata ظاهر کسب‌وکار:

- `dashboardVariant`
- `productCardVariant`
- `heroAssetKey`
- `navigationVariant`

هدف این بخش این است که UI موبایل‌فروشی، بوتیک یا سایر صنف‌ها بدون کپی کل Screenها قابل تفکیک باشد.

### theme

شناسه Theme و Tokenهای نمایشی را تعریف می‌کند. Theme Engine باید این Metadata را به Material Theme تبدیل کند.

### assets

کلیدهای Asset مخصوص صنف را نگهداری می‌کند؛ تصاویر واقعی باید در Asset Pack همان محصول قرار بگیرند.

## اضافه کردن Profile یازدهم

1. یک فایل JSON جدید در `config/business_profiles/` اضافه کنید.
2. از `schemaVersion=1` استفاده کنید.
3. تمام بخش‌های الزامی را تعریف کنید.
4. اگر قرار است Build آن Profile تولید شود، همه Profileهای دیگر را `enabled=false` و Profile جدید را `enabled=true` کنید.
5. Build و CI را اجرا کنید.
6. Runtime Test انجام دهید.

تا زمانی که Profile جدید فقط از Moduleها، Featureها و Attribute Typeهای موجود استفاده کند، نباید تغییری در Core لازم باشد.

## Profileهای فعلی

- mobile_store_001
- boutique_store_001
- beauty_store_001
- home_appliance_store_001
- auto_parts_store_001
- jewelry_store_001
- book_store_001
- grocery_store_001
- pet_store_001
- omnichannel_store_001

## اصل حفظ سورس

Profile Engine یک لایه افزایشی است. برای انتقال به این معماری، فایل‌های قبلی یا Featureهای عملیاتی نباید صرفاً برای Build حذف شوند. Compatibility با Product، Customer، Inventory، Sales، Invoice و Reports موجود باید حفظ شود.
