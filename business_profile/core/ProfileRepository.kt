package business_profile.core

/**
 * Repository layer for Business Profile access.
 * این لایه نقطه اتصال Loader و Runtime است.
 */
class ProfileRepository(
    private val loader: ProfileLoader,
    private val validator: ProfileValidator
) {
    private val profiles = mutableListOf<ProfileDefinition>()

    fun loadProfiles(source: List<Map<String, Any>>) {
        profiles.clear()
        source.forEach { raw ->
            val profile = loader.fromMap(raw)
            if (validator.isValid(profile)) {
                profiles.add(profile)
            }
        }
    }

    fun getAll(): List<ProfileDefinition> = profiles.toList()

    fun findById(id: String): ProfileDefinition? = profiles.firstOrNull { it.id == id }
}
