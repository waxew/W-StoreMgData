package com.wstore.engine.core.di

/*
====================================================
DependencyProvider

مسئولیت:
تعریف قرارداد تامین وابستگی‌های برنامه.

هدف:
جلوگیری از وابستگی مستقیم بخش‌های مختلف سیستم به یکدیگر.

این لایه برای اتصال Repository ها، Runtime و سرویس‌ها استفاده خواهد شد.
====================================================
*/

interface DependencyProvider {
    fun provideContainer(): AppContainer
}
