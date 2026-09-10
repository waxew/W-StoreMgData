package com.wstore.core.profile

/**
 * بارگذاری Runtime از تعریف JSON پروفایل.
 * این کلاس فقط مسئول تبدیل داده خارجی به مدل Runtime است.
 * منطق کسب و کار نباید داخل این لایه قرار بگیرد.
 */
class ProfileJsonRuntimeLoader(
    private val reader: JsonProfileReader,
    private val validator: ProfileValidator,
    private val loader: ProfileLoader
) {

    fun load(profileSource: ProfileDefinition): ProfileDefinition? {
        val normalized = loader.loadFromDefinition(profileSource)
        return if (validator.validate(normalized)) normalized else null
    }

    fun loadAll(sources: List<ProfileDefinition>): List<ProfileDefinition> {
        return sources.mapNotNull { load(it) }
    }
}
