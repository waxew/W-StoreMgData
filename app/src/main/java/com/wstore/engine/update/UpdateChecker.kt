package com.wstore.engine.update

import com.wstore.engine.config.AppConfigRuntimeStore
import java.net.HttpURLConnection
import java.net.URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * نام فایل: UpdateChecker.kt
 * ماژول: Update Core
 * وظیفه: بررسی Manifest نسخه از اینترنت بر اساس App Config و انتشار وضعیت برای UI/Notification.
 *
 * این کلاس URL ثابت ندارد. اگر manifestUrl در App Config خالی باشد هیچ درخواست اینترنتی انجام نمی‌شود.
 */
data class RemoteUpdateInfo(
    val versionCode: Int,
    val versionName: String,
    val downloadUrl: String,
    val notes: String
)

sealed interface UpdateCheckState {
    data object Idle : UpdateCheckState
    data object Checking : UpdateCheckState
    data object Disabled : UpdateCheckState
    data object NotConfigured : UpdateCheckState
    data object UpToDate : UpdateCheckState
    data class Available(val info: RemoteUpdateInfo) : UpdateCheckState
    data class Failed(val message: String) : UpdateCheckState
}

object UpdateRuntimeStore {
    private val _state = MutableStateFlow<UpdateCheckState>(UpdateCheckState.Idle)
    val state: StateFlow<UpdateCheckState> = _state.asStateFlow()

    internal fun setState(value: UpdateCheckState) {
        _state.value = value
    }
}

object UpdateChecker {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /** بررسی غیرهمزمان مناسب Startup یا دکمه «بررسی بروزرسانی». */
    fun checkAsync() {
        applicationScope.launch {
            checkNow()
        }
    }

    /**
     * بررسی فوری نسخه. نتیجه هم برگردانده می‌شود و هم در UpdateRuntimeStore قرار می‌گیرد.
     */
    suspend fun checkNow(): UpdateCheckState {
        val appConfig = AppConfigRuntimeStore.current()
        val updateConfig = appConfig.update

        if (!updateConfig.enabled) {
            return UpdateCheckState.Disabled.also(UpdateRuntimeStore::setState)
        }
        if (updateConfig.manifestUrl.isBlank()) {
            return UpdateCheckState.NotConfigured.also(UpdateRuntimeStore::setState)
        }

        UpdateRuntimeStore.setState(UpdateCheckState.Checking)

        val result = runCatching {
            withContext(Dispatchers.IO) {
                loadRemoteManifest(updateConfig.manifestUrl)
            }
        }.fold(
            onSuccess = { remote ->
                if (remote.versionCode > appConfig.app.versionCode) {
                    UpdateCheckState.Available(remote)
                } else {
                    UpdateCheckState.UpToDate
                }
            },
            onFailure = { error ->
                UpdateCheckState.Failed(
                    error.message ?: "بررسی نسخه جدید انجام نشد."
                )
            }
        )

        UpdateRuntimeStore.setState(result)
        return result
    }

    private fun loadRemoteManifest(url: String): RemoteUpdateInfo {
        val connection = URL(url).openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = CONNECT_TIMEOUT_MILLIS
        connection.readTimeout = READ_TIMEOUT_MILLIS
        connection.useCaches = false

        return try {
            val responseCode = connection.responseCode
            require(responseCode in 200..299) {
                "پاسخ سرور بروزرسانی نامعتبر است: $responseCode"
            }

            val body = connection.inputStream
                .bufferedReader()
                .use { reader -> reader.readText() }
            val json = JSONObject(body)

            RemoteUpdateInfo(
                versionCode = json.getInt("versionCode"),
                versionName = json.getString("versionName").trim(),
                downloadUrl = json.optString("downloadUrl", "").trim(),
                notes = json.optString("notes", "").trim()
            )
        } finally {
            connection.disconnect()
        }
    }

    private const val CONNECT_TIMEOUT_MILLIS = 8_000
    private const val READ_TIMEOUT_MILLIS = 8_000
}
