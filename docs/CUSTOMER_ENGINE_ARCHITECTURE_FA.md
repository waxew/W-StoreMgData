# معماری Customer Engine

## هدف

این بخش مسئول مدیریت اطلاعات مشتری در هسته فروشگاهی است.

## قانون معماری

Customer Engine نباید هیچ وابستگی به نوع کسب و کار داشته باشد.

نمونه های اشتباه:

- MobileCustomer
- BeautyCustomer
- BoutiqueCustomer

نمونه صحیح:

- Customer
- CustomerProfile
- CustomerHistory

## مسیر توسعه

Customer Engine در آینده شامل:

- Entity
- DAO
- Repository
- UseCase
- ViewModel
- UI

خواهد بود.
