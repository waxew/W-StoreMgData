package com.wstore.engine.ui.shell

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wstore.engine.profile.UserProfileImageStore

/**
 * نام فایل: ProfileAvatar.kt
 * ماژول: App Shell / User Profile
 * وظیفه: نمایش تصویر پروفایل و باز کردن Document Picker با لمس Avatar.
 *
 * تصویر به صورت Sampled Bitmap خوانده می‌شود تا عکس‌های بزرگ مستقیماً وارد حافظه نشوند.
 * مرحله Crop/Zoom به عنوان Editor مستقل روی همین Store اضافه می‌شود و Picker فعلی حذف نخواهد شد.
 */
@Composable
fun ProfileAvatar() {
    val context = LocalContext.current
    var selectedUri by remember {
        mutableStateOf(UserProfileImageStore.getUri(context))
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            UserProfileImageStore.saveUri(context, uri)
            selectedUri = uri
        }
    }

    val bitmap = remember(selectedUri) {
        selectedUri?.let { uri -> decodeSampledBitmap(context, uri, TARGET_SIZE_PX) }
    }

    Surface(
        modifier = Modifier
            .size(96.dp)
            .clip(CircleShape)
            .clickable {
                launcher.launch(arrayOf("image/*"))
            },
        shape = CircleShape
    ) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "تصویر پروفایل کاربر",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(96.dp)
            )
        } else {
            Box(contentAlignment = Alignment.Center) {
                Text("افزودن\nتصویر")
            }
        }
    }
}

private fun decodeSampledBitmap(
    context: Context,
    uri: Uri,
    targetSize: Int
): Bitmap? {
    val bounds = BitmapFactory.Options().apply {
        inJustDecodeBounds = true
    }

    context.contentResolver.openInputStream(uri)?.use { stream ->
        BitmapFactory.decodeStream(stream, null, bounds)
    }

    if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null

    var sampleSize = 1
    while (
        bounds.outWidth / sampleSize > targetSize * 2 ||
        bounds.outHeight / sampleSize > targetSize * 2
    ) {
        sampleSize *= 2
    }

    val options = BitmapFactory.Options().apply {
        inSampleSize = sampleSize
    }

    return context.contentResolver.openInputStream(uri)?.use { stream ->
        BitmapFactory.decodeStream(stream, null, options)
    }
}

private const val TARGET_SIZE_PX = 512
