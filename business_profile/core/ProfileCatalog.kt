package com.wstore.core.profile

/**
 * Catalog مشترک برای نگهداری Profile های بارگذاری شده.
 * UI و Backend فقط از این لایه Profile فعال را دریافت می کنند.
 */
class ProfileCatalog(
    private val loader: ProfileLoader = ProfileLoader()
) {
    private var profiles: List<ProfileDefinition> = emptyList()

    fun load(profiles: List<ProfileDefinition>) {
        this.profiles = loader.loadAll(profiles)
    }

    fun getAll(): List<ProfileDefinition> = profiles

    fun findById(id: String): ProfileDefinition? {
        return profiles.firstOrNull { it.id == id }
    }
}
