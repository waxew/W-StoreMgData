package com.wstore.engine.profile

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

/*
نام فایل:
ProfileLoader.kt

ماژول:
Business Profile Engine

وظیفه:
پیدا کردن خودکار تمام فایل‌های JSON داخل business_profiles، تبدیل آن‌ها به ProfileDefinition
و اعتبارسنجی قبل از ورود به ProfileRegistry.

اصل معماری:
نام هیچ Profile در این فایل Hard Code نشده است؛ بنابراین Profile یازدهم با اضافه کردن فایل JSON
به پوشه Config به صورت خودکار کشف می‌شود.
*/

class ProfileLoader(
    private val context: Context,
    private val validator: ProfileSchemaValidator = ProfileSchemaValidator()
) {

    fun loadAll(): List<ProfileDefinition> {
        val fileNames = context.assets.list(PROFILE_DIRECTORY)
            ?.filter { fileName ->
                fileName.endsWith(JSON_EXTENSION) && !fileName.startsWith(SCHEMA_FILE_PREFIX)
            }
            ?.sorted()
            .orEmpty()

        require(fileNames.isNotEmpty()) {
            "هیچ Business Profile در مسیر $PROFILE_DIRECTORY پیدا نشد."
        }

        return fileNames.map { fileName ->
            val jsonText = context.assets
                .open("$PROFILE_DIRECTORY/$fileName")
                .bufferedReader()
                .use { reader -> reader.readText() }

            parseProfile(JSONObject(jsonText)).also(validator::validate)
        }
    }

    private fun parseProfile(json: JSONObject): ProfileDefinition {
        val identity = json.getJSONObject("identity")
        val ui = json.getJSONObject("uiProfile")
        val theme = json.getJSONObject("theme")
        val assets = json.getJSONObject("assets")

        return ProfileDefinition(
            schemaVersion = json.getInt("schemaVersion"),
            id = json.getString("id").trim(),
            name = identity.getString("name").trim(),
            businessType = identity.getString("businessType").trim(),
            enabled = json.getBoolean("enabled"),
            modules = parseModules(json.getJSONObject("modules")),
            features = parseEnabledKeys(json.getJSONObject("features")),
            attributes = parseAttributes(json.optJSONArray("attributes") ?: JSONArray()),
            uiProfile = ProfileUiDefinition(
                dashboardVariant = ui.getString("dashboardVariant").trim(),
                productCardVariant = ui.getString("productCardVariant").trim(),
                heroAssetKey = ui.optString("heroAssetKey", "dashboard_hero").trim(),
                navigationVariant = ui.optString("navigationVariant", "default").trim()
            ),
            theme = ProfileThemeDefinition(
                name = theme.getString("name").trim(),
                primaryToken = theme.optString("primaryToken", "primary").trim(),
                backgroundToken = theme.optString("backgroundToken", "background").trim(),
                useDynamicColor = theme.optBoolean("useDynamicColor", false)
            ),
            assets = ProfileAssetDefinition(
                iconKey = assets.optString("iconKey", "app_icon").trim(),
                logoKey = assets.optString("logoKey", "app_logo").trim(),
                dashboardHeroKey = assets.optString("dashboardHeroKey", "dashboard_hero").trim(),
                emptyStateKey = assets.optString("emptyStateKey", "empty_state").trim()
            )
        )
    }

    private fun parseModules(json: JSONObject): List<ProfileModuleDefinition> =
        json.keys().asSequence().map { moduleId ->
            ProfileModuleDefinition(
                id = moduleId.trim(),
                enabled = json.getBoolean(moduleId)
            )
        }.sortedBy { it.id }.toList()

    private fun parseEnabledKeys(json: JSONObject): List<String> =
        json.keys().asSequence()
            .filter { key -> json.optBoolean(key, false) }
            .map(String::trim)
            .sorted()
            .toList()

    private fun parseAttributes(array: JSONArray): List<ProfileAttributeDefinition> =
        (0 until array.length()).map { index ->
            val item = array.getJSONObject(index)
            ProfileAttributeDefinition(
                key = item.getString("key").trim(),
                label = item.getString("label").trim(),
                type = item.getString("type").trim(),
                required = item.optBoolean("required", false),
                visible = item.optBoolean("visible", true),
                options = item.optJSONArray("options")?.toStringList().orEmpty()
            )
        }

    private fun JSONArray.toStringList(): List<String> =
        (0 until length()).map { index -> getString(index).trim() }

    companion object {
        const val PROFILE_DIRECTORY = "business_profiles"
        private const val JSON_EXTENSION = ".json"
        private const val SCHEMA_FILE_PREFIX = "schema_"
    }
}
