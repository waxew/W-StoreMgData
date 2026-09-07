package core.domain

/**
 * نام فایل: BusinessProfile.kt
 * ماژول: Core Domain
 * وظیفه: تعریف مدل اصلی پروفایل کسب و کار
 * ارتباط: BusinessRegistry و Module Loader
 */

data class BusinessProfile(
    val id: String,
    val name: String,
    val businessType: String,
    val enabledModules: List<String>,
    val attributes: Map<String, String>
)
