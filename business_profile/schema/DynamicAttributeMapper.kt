package business_profile.schema

/**
 * مپ کردن Attribute های پویا به ساختار قابل استفاده در Runtime.
 * این بخش اجازه می دهد Profile های مختلف بدون تغییر Core فیلدهای متفاوت داشته باشند.
 */
class DynamicAttributeMapper {
    fun map(definitions: List<AttributeDefinition>): List<AttributeDefinition> {
        return definitions
    }
}
