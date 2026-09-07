# Dynamic Attribute Components Progress

## هدف

تعریف لایه مشترک کامپوننت های UI برای Attribute های پویا.

## قانون معماری

UI نباید بداند محصول مربوط به موبایل، بوتیک یا آرایشی است.

فقط این مسیر را دنبال می کند:

Attribute Schema -> Attribute Definition -> Attribute Type -> UI Component

## Mapping اولیه

TEXT -> TextField
NUMBER -> NumberField
DATE -> DatePicker
ENUM -> Dropdown
BOOLEAN -> Switch
