package com.wstore.engine.backup

import android.content.Context
import androidx.core.content.FileProvider
import java.io.File

/**
 * مدیریت پایه پشتیبان‌گیری دیتابیس.
 *
 * این لایه فقط عملیات Export را فراهم می‌کند و هیچ حذف یا جایگزینی روی دیتابیس اصلی انجام نمی‌دهد.
 * Restore در مرحله بعد با تایید کاربر و بررسی نسخه Schema اضافه می‌شود.
 */
object DatabaseBackupManager {

    fun createBackupFile(context: Context): File {
        val source = context.getDatabasePath("wstore_database")
        require(source.exists()) {
            "فایل دیتابیس برای پشتیبان‌گیری وجود ندارد."
        }

        val backupDir = File(context.filesDir, "backup").apply {
            mkdirs()
        }

        val target = File(
            backupDir,
            "wstore_database_${System.currentTimeMillis()}.backup"
        )

        source.copyTo(target, overwrite = false)
        return target
    }
}
