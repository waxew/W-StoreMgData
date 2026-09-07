/*
================================================
نام فایل:
ApplicationRuntime.kt

وظیفه:
لایه هماهنگ‌کننده اجرای Runtime برنامه.

مسیر آینده:
Application
    ↓
ApplicationRuntime
    ↓
Business Profile
    ↓
Modules
    ↓
UI

این کلاس برای جلوگیری از وابستگی Activity به منطق Core ایجاد می‌شود.

================================================
*/

package com.wstore.engine.runtime

class ApplicationRuntime {

    // در مراحل بعدی بارگذاری Business Profile و Module ها انجام می‌شود.
    fun initialize() {
        // Runtime initialization
    }
}
