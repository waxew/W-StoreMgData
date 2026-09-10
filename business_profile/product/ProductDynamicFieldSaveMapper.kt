package business_profile.product

/**
 * مپ کردن وضعیت Dynamic Field قبل از ذخیره محصول.
 * Product Core مستقل باقی می‌ماند.
 */
class ProductDynamicFieldSaveMapper {
    fun map(fields: Map<String, String>): Map<String, String> {
        return fields.toMap()
    }
}
