# راهنمای فارسی جریان کامل مشتری

## معماری مشتری

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

Database

## قوانین

- مشتری یک قابلیت عمومی سیستم است.
- اطلاعات اختصاصی هر کسب و کار باید از Business Profile و Attribute Engine مدیریت شود.
- هیچ وابستگی به نام کسب و کار داخل Core قرار نمی گیرد.
