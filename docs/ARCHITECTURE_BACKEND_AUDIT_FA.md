# ممیزی معماری و Backend پروژه W-StoreMgData

این سند وضعیت معماری واقعی پروژه را بر اساس کد فعلی ثبت می‌کند و برای جلوگیری از ساده‌سازی یا حذف Featureها در Buildهای بعدی نگهداری می‌شود.

## 1. معماری فعلی

جریان اصلی Featureها:

```text
Compose Screen
    ↓
ViewModel
    ↓
Repository
    ↓
Room DAO
    ↓
SQLite
```

فعال‌سازی Featureها مستقل از Navigation و بر اساس Business Profile انجام می‌شود:

```text
BusinessProfile
    ↓
BusinessRegistry
    ↓
RuntimeModuleService
    ↓
Dashboard / ModuleRouteRegistry
```

این جداسازی باید حفظ شود. Dashboard نباید Feature را فعال یا غیرفعال کند و UI نباید مستقیم به DAO دسترسی داشته باشد.

## 2. وضعیت Data Integrity

### Sales
ثبت فروش در یک Room Transaction انجام می‌شود. در همان Transaction:

- اعتبارسنجی موجودی انجام می‌شود.
- Sale ثبت می‌شود.
- SaleItem Snapshot ثبت می‌شود.
- موجودی Product کاهش پیدا می‌کند.
- InventoryTransaction با نوع SALE ثبت می‌شود.

در صورت خطای هر مرحله کل عملیات Rollback می‌شود.

### Product
حذف Product در Repository کنترل می‌شود. اگر Product در Sale یا Invoice سابقه داشته باشد حذف فیزیکی مسدود می‌شود.

### Customer
در این مرحله حذف Customer نیز کنترل شد. اگر مشتری سابقه Sales داشته باشد حذف فیزیکی مسدود می‌شود تا Customer Detail و تاریخچه خرید قابل بازیابی بماند.

### Invoice
Invoice و InvoiceItem Snapshot مستقل از تغییر بعدی Product یا Customer نگهداری می‌شوند.

## 3. Reporting Read Model

ReportsRepository یک Read Model است و هیچ مسیر نوشتنی ایجاد نمی‌کند.

```text
SalesRepository ───────┐
ProductRepository ─────┼── ReportsRepository ── ReportsViewModel ── ReportsScreen
InventoryRepository ───┘
```

گزارش‌ها شامل موارد زیر هستند:

- تعداد فروش
- مجموع فروش
- میانگین مبلغ فروش
- تعداد کالا
- تعداد کل واحد موجود
- ارزش فروش موجودی فعلی
- تعداد کالاهای کم‌موجودی
- تعداد کالاهای ناموجود
- پنج کالای پرفروش بر اساس SaleItem
- ده گردش اخیر موجودی

برای پرفروش‌ها Query تجمیعی مستقیماً روی sale_items اجرا می‌شود و جدول گزارش تکراری ساخته نشده است.

## 4. نواقص معماری باقی‌مانده

### اولویت بالا

1. مبلغ‌ها هنوز با `Double` ذخیره می‌شوند. برای نسخه مالی پایدار باید در Migration کنترل‌شده به مقدار صحیح کوچک‌ترین واحد پول (`Long`) یا ساختار Money منتقل شوند تا خطای Floating Point حذف شود.
2. تست‌های Unit/DAO/Migration هنوز پوشش کافی ندارند. قبل از Release نهایی باید تست Transactionهای Sales، Inventory و Migrationها اضافه شود.
3. IMEI و Warranty هنوز Feature واقعی UI/Data کامل ندارند و فعلاً Runtime آن‌ها را فعال می‌داند ولی Route به Placeholder می‌رسد.
4. Export/Backup داده کاربر هنوز وجود ندارد.
5. داده‌ها Local-first هستند و Sync/Auth/Remote Backend هنوز پیاده‌سازی نشده است. این موضوع نقص Build نیست، ولی برای نسخه چنددستگاهی یک لایه Sync جدا لازم است.

### اولویت متوسط

1. Pagination برای لیست‌های بزرگ Sales/Customer/Product وجود ندارد.
2. Search و Filter پیشرفته گزارش‌ها بر اساس تاریخ وجود ندارد.
3. Customer Delete به‌جای Soft Delete فعلاً Guarded Delete است. برای کسب‌وکارهایی که آرشیو لازم دارند، ستون active/archived در Migration بعدی مناسب‌تر است.
4. گزارش سود واقعی امکان‌پذیر نیست چون Product فعلاً قیمت خرید/Cost ندارد و فقط `price` نگهداری می‌شود.

## 5. قواعد غیرقابل نقض توسعه

- برای سبز شدن Build هیچ Feature یا فایل کسب‌وکاری حذف نشود.
- تغییر موجودی فقط از Inventory یا Transaction فروش انجام شود.
- UI مستقیم DAO را صدا نزند.
- Migrationهای Room حذف یا دور زده نشوند.
- Snapshotهای Sales/Invoice دستکاری نشوند.
- Featureهای ناقص به Placeholder هدایت شوند، نه اینکه از Runtime یا Source حذف شوند.
- قبل از تغییرات بزرگ Backup Branch ساخته شود.

## 6. هدف مراحل بعدی

ترتیب پیشنهادی ادامه توسعه:

1. تکمیل IMEI Module
2. تکمیل Warranty Module
3. فیلتر بازه زمانی و Export گزارش‌ها
4. تست‌های Room/Repository/Transaction
5. بهبود UX و Validation
6. Runtime test روی دستگاه واقعی
7. Signed Release نهایی
