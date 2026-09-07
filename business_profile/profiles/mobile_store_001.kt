/*
================================================

نام فایل:
mobile_store_001.kt

وظیفه:
تعریف پروفایل نسخه موبایل فروشی.

این فایل فقط تنظیمات کسب و کار را نگهداری می‌کند.
منطق برنامه نباید داخل Profile قرار گیرد.

ماژول‌های اختصاصی:
IMEI و Warranty باید به صورت Module مستقل پیاده‌سازی شوند.

ویژگی‌های ساده:
Brand و Color و Storage به صورت Attribute Schema مدیریت می‌شوند.

================================================
*/

object MobileStore001Profile {

    const val ID = "mobile_store_001"

    const val TYPE = "MOBILE_STORE"

    val modules = listOf(
        "customer",
        "product",
        "inventory",
        "sales",
        "invoice",
        "imei",
        "warranty",
        "repair"
    )

    val productAttributes = listOf(
        "brand",
        "model",
        "storage",
        "color",
        "serial_number"
    )
}
