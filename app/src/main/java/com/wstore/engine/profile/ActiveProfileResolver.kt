package com.wstore.engine.profile

/*
نام فایل:
ActiveProfileResolver.kt

ماژول:
Business Profile Engine

وظیفه:
انتخاب Profile فعال بر اساس enabled=true.

قانون پروژه:
توسعه‌دهنده Profile موردنظر را فعال می‌کند و در هر Build باید دقیقاً یک Profile فعال باشد.
کاربر نهایی از داخل برنامه نوع کسب‌وکار را انتخاب نمی‌کند.
*/

class ActiveProfileResolver {

    fun resolve(registry: ProfileRegistry): ProfileDefinition {
        val enabled = registry.enabledProfiles()

        require(enabled.size == 1) {
            when {
                enabled.isEmpty() ->
                    "هیچ Business Profile فعالی وجود ندارد. دقیقاً یک Profile باید enabled=true باشد."
                else ->
                    "بیش از یک Business Profile فعال است: ${enabled.joinToString { it.id }}. فقط یک Profile باید enabled=true باشد."
            }
        }

        return enabled.single()
    }
}
