package business_profile.registry

/**
 * Registry مرکزی پروفایل های کسب و کار.
 *
 * هدف:
 * نگهداری لیست Profile های قابل استفاده بدون وابستگی Core به یک کسب و کار خاص.
 */
class ProfileRegistry {

    private val profiles = mutableMapOf<String, Any>()

    fun register(id: String, profile: Any) {
        profiles[id] = profile
    }

    fun get(id: String): Any? {
        return profiles[id]
    }

    fun getAll(): List<Any> {
        return profiles.values.toList()
    }
}
