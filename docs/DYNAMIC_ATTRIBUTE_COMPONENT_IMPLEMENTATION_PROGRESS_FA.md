# Dynamic Attribute Component Implementation

## هدف

اتصال Attribute Engine به لایه Presentation بدون وابستگی به نوع کسب و کار.

## معماری

AttributeDefinition

↓

Field Resolver

↓

Dynamic Component

↓

Compose UI

## قوانین

- Core نباید نام کسب و کارها را بشناسد.
- Component ها فقط بر اساس AttributeType ساخته می شوند.
- IMEI، Size، Color و Warranty فقط نمونه Attribute هستند.
