package business_profile.runtime

/**
 * تعیین وضعیت ماژول های فعال بر اساس Business Profile.
 */
class ProfileModuleStateResolver {
    fun resolve(enabledModules: List<String>): Set<String> {
        return enabledModules.toSet()
    }
}
