package com.wstore.engine.profile

/*
نام فایل:
ProfileSchemaValidator.kt

ماژول:
Business Profile Engine

وظیفه:
اعتبارسنجی ساختاری Profile قبل از ورود به Registry و Runtime.
این Validator نام کسب‌وکارها را نمی‌شناسد و فقط قواعد عمومی Schema را بررسی می‌کند.

هدف:
یک Profile جدید تا زمانی که قرارداد استاندارد را رعایت کند بدون تغییر Core قابل اضافه شدن باشد.
*/

class ProfileSchemaValidator {

    fun validate(profile: ProfileDefinition) {
        require(profile.schemaVersion == SUPPORTED_SCHEMA_VERSION) {
            "نسخه Schema پشتیبانی نمی‌شود: ${profile.schemaVersion}"
        }
        require(profile.id.isNotBlank()) { "شناسه Profile خالی است." }
        require(profile.name.isNotBlank()) { "نام Profile خالی است." }
        require(profile.businessType.isNotBlank()) { "نوع کسب‌وکار Profile خالی است." }
        require(profile.modules.isNotEmpty()) { "حداقل یک Module باید در Profile تعریف شود." }
        require(profile.modules.none { it.id.isBlank() }) { "Module بدون شناسه معتبر است." }
        require(profile.modules.map { it.id }.distinct().size == profile.modules.size) {
            "شناسه Module تکراری در Profile ${profile.id} وجود دارد."
        }
        require(profile.features.none { it.isBlank() }) { "Feature با شناسه خالی وجود دارد." }
        require(profile.features.distinct().size == profile.features.size) {
            "Feature تکراری در Profile ${profile.id} وجود دارد."
        }
        require(profile.attributes.map { it.key }.distinct().size == profile.attributes.size) {
            "Attribute تکراری در Profile ${profile.id} وجود دارد."
        }

        profile.attributes.forEach { attribute ->
            require(attribute.key.isNotBlank()) { "Attribute بدون key مجاز نیست." }
            require(attribute.label.isNotBlank()) { "Attribute ${attribute.key} بدون label است." }
            require(attribute.type in SUPPORTED_ATTRIBUTE_TYPES) {
                "نوع Attribute پشتیبانی نمی‌شود: ${attribute.type}"
            }
            if (attribute.type == TYPE_OPTION) {
                require(attribute.options.isNotEmpty()) {
                    "Attribute انتخابی ${attribute.key} باید حداقل یک option داشته باشد."
                }
            }
        }

        require(profile.uiProfile.dashboardVariant.isNotBlank()) { "dashboardVariant تعریف نشده است." }
        require(profile.uiProfile.productCardVariant.isNotBlank()) { "productCardVariant تعریف نشده است." }
        require(profile.theme.name.isNotBlank()) { "Theme Profile تعریف نشده است." }
    }

    companion object {
        const val SUPPORTED_SCHEMA_VERSION = 1
        const val TYPE_OPTION = "option"

        val SUPPORTED_ATTRIBUTE_TYPES: Set<String> = setOf(
            "text",
            "number",
            "decimal",
            "date",
            "boolean",
            TYPE_OPTION,
            "barcode",
            "serial"
        )
    }
}
