# معماری لایه Presentation

## هدف

لایه Presentation مسئول ارتباط بین رابط کاربری و منطق برنامه است.

مسیر ارتباط:

UI

↓

ViewModel

↓

UseCase

↓

Repository

## قوانین

- هیچ منطق کسب و کار خاصی در این لایه قرار نمی‌گیرد.
- نام‌هایی مانند mobile یا beauty نباید در Package و Class های اصلی استفاده شوند.
- تفاوت کسب و کارها فقط از Business Profile و Module ها خوانده می‌شود.
