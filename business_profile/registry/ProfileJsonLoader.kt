package business_profile.registry

/**
 * بارگذاری Profile ها از فایل های JSON
 * این لایه باعث می شود اضافه کردن پروفایل جدید نیاز به تغییر Core نداشته باشد.
 */
class ProfileJsonLoader {
    fun loadProfile(json: String): String {
        // در نسخه های بعدی Parsing واقعی JSON با Serializer انجام می شود.
        return json
    }
}
