/*
================================================
نام فایل:
core/build.gradle.kts

ماژول:
Core Engine

وظیفه:
کامپایل کردن سورس‌های موجود هسته فروشگاهی به عنوان یک ماژول مستقل.

نکته مهم:
هیچ فایل Core جابه‌جا یا حذف نمی‌شود. مسیرهای فعلی پروژه به عنوان Source Set استفاده می‌شوند.
================================================
*/

plugins {
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(17)

    sourceSets {
        main {
            kotlin.srcDirs(
                "attribute",
                "business",
                "di",
                "domain",
                "module",
                "product"
            )
        }
    }
}
