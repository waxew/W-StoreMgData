package business_profile.registry

// اعتبارسنجی ساختار پروفایل کسب و کار
// این کلاس از ورود Profile ناقص به Runtime جلوگیری می کند.

class ProfileValidator {

    fun validate(profileId: String, modules: List<String>): Boolean {
        if (profileId.isBlank()) return false
        if (modules.isEmpty()) return false
        return true
    }
}
