package com.wstore.core.profile

/**
 * مدل پایه Business Profile
 * هر کسب و کار از طریق این ساختار تعریف می شود.
 */
data class ProfileDefinition(
    val id: String,
    val name: String,
    val enabled: Boolean,
    val modules: List<String>,
    val features: List<String>,
    val uiProfile: String
)
