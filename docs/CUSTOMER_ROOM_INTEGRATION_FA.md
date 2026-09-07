# اتصال Customer Engine به Database

## هدف

این سند مسیر اتصال ماژول مشتری به لایه ذخیره‌سازی را توضیح می‌دهد.

ساختار:

Customer UI

↓

ViewModel

↓

UseCase

↓

Repository

↓

DataSource

↓

DAO

↓

Database

## قوانین معماری

- Customer یک قابلیت عمومی است.
- هیچ Business Profile نباید داخل این لایه قرار بگیرد.
- اطلاعات اختصاصی کسب‌وکار فقط از طریق Business Profile و Moduleها اضافه می‌شوند.
