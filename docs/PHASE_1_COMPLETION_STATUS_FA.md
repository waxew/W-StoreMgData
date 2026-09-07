# وضعیت تکمیل فاز ۱ پروژه W-StoreMgData

## هدف فاز ۱

ساخت زیرساخت پایه قابل Build برای برنامه فروشگاهی.

## تکمیل شده

- ساختار Clean Architecture
- لایه Data
- Entity مشتری
- DAO مشتری
- Room Database
- Database Provider
- Repository Layer Foundation
- اتصال اولیه UI تا Data

## جریان داده

UI

↓

ViewModel

↓

UseCase

↓

Repository

↓

DataSource

↓

Room Database

## قوانین معماری

Core و Data نباید وابسته به نوع فروشگاه باشند.

Mobile، Boutique و Beauty فقط Business Profile هستند و نباید در کد اصلی ظاهر شوند.

## باقی مانده برای بستن کامل فاز ۱

- اضافه کردن Dependency های واقعی Gradle
- اتصال Hilt
- اجرای Gradle Sync
- Build Debug APK
- رفع خطاهای Compile
