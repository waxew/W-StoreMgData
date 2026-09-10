package com.wstore.engine.profile

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * نام فایل: UserProfileImageStore.kt
 * ماژول: App Shell / User Profile
 * وظیفه: نگهداری URI تصویر پروفایل انتخاب‌شده توسط کاربر در SharedPreferences.
 *
 * URI از Document Picker گرفته می‌شود و مجوز خواندن پایدار نگهداری می‌شود تا پس از اجرای مجدد
 * برنامه نیز تصویر قابل دسترسی باشد. فایل اصلی کاربر کپی یا حذف نمی‌شود.
 */
object UserProfileImageStore {
    private const val PREFS_NAME = "wstore_user_profile"
    private const val KEY_PROFILE_IMAGE_URI = "profile_image_uri"

    fun getUri(context: Context): Uri? {
        val value = context
            .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_PROFILE_IMAGE_URI, null)
            ?: return null
        return runCatching { Uri.parse(value) }.getOrNull()
    }

    fun saveUri(context: Context, uri: Uri) {
        runCatching {
            context.contentResolver.takePersistableUriPermission(
                uri,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )
        }

        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_PROFILE_IMAGE_URI, uri.toString())
            .apply()
    }

    fun clear(context: Context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(KEY_PROFILE_IMAGE_URI)
            .apply()
    }
}
