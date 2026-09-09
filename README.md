# W-StoreMgData

## موتور فروشگاهی White Label مبتنی بر Business Profile

W-StoreMgData یک پروژه اندرویدی Kotlin/Compose برای ساخت چند برنامه فروشگاهی مستقل از یک Core مشترک است. هدف پروژه این است که منطق اصلی فروش، مشتری، موجودی، فاکتور، گزارش و دیتابیس یک بار توسعه داده شود و تفاوت هر کسب‌وکار از طریق Config و Business Profile اعمال شود.

اصل معماری:

```text
Core Engine
    +
App Config
    +
Business Profile
    +
Feature Modules
    +
UI/Profile Metadata
    =
Application مستقل
```

---

## اصل عدم حذف و کوچک‌سازی

در این پروژه سبز شدن Build هرگز دلیل حذف Feature، فایل، معماری یا ساده‌سازی مصنوعی پروژه نیست. هر اصلاح باید روی ساختار واقعی پروژه انجام شود و قابلیت‌های قبلی حفظ شوند.

قبل از Refactorهای بزرگ از Branch پشتیبان استفاده می‌شود.

---

## App Config مرکزی

فایل اصلی تنظیمات برنامه:

```text
config/app_config.json
```

این فایل منبع مرکزی اطلاعاتی است که نباید در بخش‌های مختلف کد Hard Code شوند، از جمله:

- نام برنامه
- `applicationId`
- `versionCode`
- `versionName`
- کلید لوگو و آیکون
- اطلاعات AS Team و ایمیل پشتیبانی
- تنظیمات Logo Motion
- سیاست Ads و VIP
- تنظیمات Update Checker
- Backup / Restore
- تنظیمات App Shell، Drawer و Top Bar

فایل `app/build.gradle.kts` همین Config را در زمان Build می‌خواند؛ بنابراین نام پکیج و نسخه از همان منبع مرکزی تنظیم می‌شوند. پوشه `config/` نیز به Assets اضافه می‌شود تا Runtime همان Config را مصرف کند.

---

## Business Profile Engine

تمام Profileها در مسیر زیر قرار دارند:

```text
config/business_profiles/
```

هر Profile شامل این بخش‌هاست:

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

قانون انتخاب Profile:

```text
دقیقاً یک Profile باید enabled=true باشد.
```

کاربر نهایی نوع کسب‌وکار را انتخاب نمی‌کند. توسعه‌دهنده/ناشر Profile موردنظر را فعال می‌کند و Build خروجی همان کسب‌وکار را تولید می‌کند.

جریان Runtime:

```text
JSON Profiles
     ↓
ProfileLoader
     ↓
ProfileSchemaValidator
     ↓
ProfileRegistry
     ↓
ActiveProfileResolver
     ↓
ProfileRuntimeStore
     ↓
BusinessRegistry(Core)
     ↓
Modules / UI / Feature Engine
```

اضافه کردن Profile یازدهم نباید نیازمند تغییر Core باشد؛ در حالت عادی فقط یک JSON جدید مطابق Schema اضافه می‌شود.

---

## ۱۰ Business Profile استاندارد

پروفایل‌های فعلی پروژه:

1. `mobile_store_001` — فروشگاه موبایل
2. `boutique_store_001` — بوتیک پوشاک
3. `beauty_store_001` — فروشگاه آرایشی و بهداشتی
4. `home_appliance_store_001` — فروشگاه لوازم خانگی
5. `auto_parts_store_001` — فروشگاه قطعات خودرو
6. `jewelry_store_001` — فروشگاه طلا و جواهر
7. `book_store_001` — کتاب‌فروشی
8. `grocery_store_001` — فروشگاه مواد غذایی
9. `pet_store_001` — فروشگاه حیوانات خانگی
10. `omnichannel_store_001` — فروشگاه چندکاناله

در Config فعلی فقط `mobile_store_001` فعال است و ۹ مورد دیگر `enabled=false` هستند.

---

## معماری Backend

جریان اصلی داده:

```text
Compose UI
    ↓
ViewModel
    ↓
Repository
    ↓
Room DAO
    ↓
SQLite
```

ماژول‌های عملیاتی فعلی:

- Product CRUD
- Customer CRUD
- Customer Purchase History
- Inventory In/Out
- Sales + Sale Detail
- Invoice
- Dashboard Analytics
- Reports

عملیات Sales و Inventory به صورت Transaction انجام می‌شوند و موجودی منفی کنترل می‌شود. حذف Product دارای سابقه تجاری و Customer دارای سابقه Sales نیز کنترل شده است تا تاریخچه از بین نرود.

---

## Dynamic Attribute Architecture

هر کسب‌وکار Attributeهای مخصوص خودش را از Profile تعریف می‌کند.

نمونه موبایل:

```text
IMEI
Storage
Color
Warranty
Serial
```

نمونه بوتیک:

```text
Size
Color
Material
Season
Collection
```

نمونه آرایشی:

```text
Brand
Volume
Expiration
Batch
Skin Type
```

Core نباید برای هر صنف Product Entity جداگانه ایجاد کند. Profile Schema منبع تعریف فیلدهای اختصاصی است و UI/Form Engine باید براساس همین Metadata رندر شود.

---

## UI Profile و Assets

هر Profile Metadata مستقل برای ظاهر دارد:

```text
dashboardVariant
productCardVariant
navigationVariant
heroAssetKey
theme
assets
```

هدف این است که موبایل‌فروشی، بوتیک، آرایشی و سایر Profileها ظاهر و المان‌های متناسب با صنف خود داشته باشند، اما Shared UI Components تکرار نشوند.

اصل طراحی:

```text
Shared UI Components
        +
Business UI Profile
        +
Theme / Assets
        =
UI مخصوص کسب‌وکار
```

---

## Ads و VIP

Policy تبلیغات از `app_config.json` خوانده می‌شود.

منطق پایه:

```text
Ads globally disabled → بدون تبلیغ
VIP فعال → بدون تبلیغ
Guest → طبق showForGuest
اشتراک منقضی → طبق showForExpiredSubscription
```

SDK تبلیغاتی نباید مستقیم به Core وابسته شود. `AdsPolicyResolver` فقط تصمیم نمایش را محاسبه می‌کند و Provider در لایه Integration قرار می‌گیرد.

---

## Update، Notification و Backup

Config مرکزی برای این بخش‌ها آماده شده است:

- بررسی نسخه جدید در Start
- Popup بروزرسانی
- Notification Badge
- Backup کل اطلاعات کاربر
- Restore از فایل انتخابی

پیاده‌سازی سرویس‌های اجرایی این بخش‌ها باید از Config مرکزی استفاده کند و اطلاعات ثابت در Screenها تکرار نشوند.

---

## استاندارد Navigation

Navigation بین صفحات داخلی یک Feature می‌تواند Back عادی داشته باشد.

اما ورود به Root Feature از Drawer یا منوی اصلی باید Stack قبلی را کنار بگذارد تا Back کاربر را مستقیم به Home برگرداند، نه به Feature قبلی.

```text
Home → T → Drawer → C → Back → Home
```

---

## استاندارد کدنویسی

- فایل‌های جدید و فایل‌های مهم اصلاح‌شده دارای توضیح فارسی باشند.
- UI نباید مستقیم به DAO متصل شود.
- منطق تجاری داخل Composable قرار نگیرد.
- Profile خاص داخل Core Hard Code نشود.
- اطلاعات ثابت برنامه از App Config خوانده شوند.
- Featureهای صنفی از Business Profile/Schema خوانده شوند.
- Migrationهای Room مخرب نباشند.
- برای سبز شدن CI هیچ Feature یا فایل پروژه حذف نشود.

---

## فرآیند ساخت نسخه White Label جدید

```text
1. ویرایش config/app_config.json
2. فعال کردن فقط یک Business Profile
3. تنظیم Theme/Asset همان Profile
4. Build
5. Schema Validation
6. Android CI
7. Debug/Release Build Check
8. Runtime Test روی دستگاه
9. Signed Release
```

برای ساخت یک محصول جدید، Core اصلی نباید کپی شود.

---

## مستندات فنی

مستندات فارسی معماری در `docs/` نگهداری می‌شوند. فایل‌های اصلی مرتبط با این Refactor:

- `docs/PROFILE_REGISTRY_SCHEMA_FA.md`
- `docs/PROFILE_ENGINE_ROADMAP_FA.md`
- `docs/ARCHITECTURE_BACKEND_AUDIT_FA.md`
- `docs/APP_CONFIG_GUIDE_FA.md`
- `docs/BUSINESS_PROFILE_GUIDE_FA.md`

---

## وضعیت فعلی

زیرساخت‌های موجود پروژه حفظ شده‌اند و Refactor فعلی در حال تبدیل Runtime قبلی Hard Coded به موتور Config/Profile-driven است. Build سبز شرط لازم است اما Runtime Test و Signed Release مراحل جداگانه محسوب می‌شوند.
