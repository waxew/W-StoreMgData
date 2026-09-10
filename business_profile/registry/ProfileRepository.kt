package business_profile.registry

/**
 * مخزن استاندارد مدیریت Profile های کسب و کار.
 * این لایه برای جدا کردن منطق برنامه از منبع داده Profile ایجاد شده است.
 */
interface ProfileRepository {
    fun getProfiles(): List<BusinessProfileDefinition>
    fun getActiveProfile(): BusinessProfileDefinition?
}

/**
 * مدل پایه Profile.
 * نسخه کامل با اتصال واقعی JSON در مراحل بعدی تکمیل می شود.
 */
data class BusinessProfileDefinition(
    val id: String,
    val name: String,
    val modules: List<String>,
    val attributes: List<String>,
    val ui: String
)
