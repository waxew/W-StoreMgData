# وضعیت Dynamic Attribute UI Renderer

## هدف

ایجاد لایه‌ای که Attribute Definition را به Component مناسب UI تبدیل کند.

## جریان

Business Profile

↓

Attribute Schema

↓

Attribute Definition

↓

Dynamic Renderer

↓

UI Component

## قوانین معماری

- Renderer نباید نوع کسب و کار را بشناسد.
- Mobile، Boutique و Beauty فقط Profile هستند.
- انتخاب Component فقط بر اساس AttributeType انجام می‌شود.

## نمونه

TEXT → TextField

DATE → DatePicker

ENUM → Dropdown
