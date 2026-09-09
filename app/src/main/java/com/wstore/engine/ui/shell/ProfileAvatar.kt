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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.wstore.engine.profile.UserProfileImageStore
import java.io.File
import java.io.FileOutputStream
import kotlin.math.min

/**
 * نام فایل: ProfileAvatar.kt
 * ماژول: App Shell / User Profile
 * وظیفه: انتخاب، پیش‌نمایش، Zoom و Crop مرکزی تصویر پروفایل و ذخیره نسخه نهایی در فضای داخلی برنامه.
 *
 * فایل اصلی کاربر تغییر نمی‌کند. پس از تأیید، یک تصویر مربعی 512×512 ساخته می‌شود که در UI
 * به شکل دایره نمایش داده می‌شود. Decode اولیه نیز Sampled است تا مصرف حافظه کنترل شود.
 */
@Composable
fun ProfileAvatar() {
    val context = LocalContext.current
    var selectedUri by remember {
        mutableStateOf(UserProfileImageStore.getUri(context))
    }
    var pendingBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var zoom by remember { mutableFloatStateOf(1f) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri ->
        if (uri != null) {
            pendingBitmap = decodeSampledBitmap(context, uri, EDITOR_SOURCE_SIZE_PX)
            zoom = 1f
        }
    }

    val bitmap = remember(selectedUri) {
        selectedUri?.let { uri -> decodeSampledBitmap(context, uri, PROFILE_OUTPUT_SIZE_PX) }
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

    pendingBitmap?.let { sourceBitmap ->
        AlertDialog(
            onDismissRequest = { pendingBitmap = null },
            title = { Text("تنظیم تصویر پروفایل") },
            text = {
                Column {
                    Box(
                        modifier = Modifier
                            .size(220.dp)
                            .clip(CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            bitmap = sourceBitmap.asImageBitmap(),
                            contentDescription = "پیش‌نمایش Crop",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer(
                                    scaleX = zoom,
                                    scaleY = zoom
                                )
                        )
                    }
                    Text("بزرگ‌نمایی")
                    Slider(
                        value = zoom,
                        onValueChange = { value -> zoom = value },
                        valueRange = 1f..3f
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val cropped = createCenteredCrop(
                            bitmap = sourceBitmap,
                            zoom = zoom,
                            outputSize = PROFILE_OUTPUT_SIZE_PX
                        )
                        val finalUri = saveProfileBitmap(context, cropped)
                        UserProfileImageStore.saveUri(context, finalUri)
                        selectedUri = finalUri
                        pendingBitmap = null
                    }
                ) {
                    Text("ذخیره تصویر")
                }
            },
            dismissButton = {
                TextButton(onClick = { pendingBitmap = null }) {
                    Text("انصراف")
                }
            }
        )
    }
}

private fun createCenteredCrop(
    bitmap: Bitmap,
    zoom: Float,
    outputSize: Int
): Bitmap {
    val baseSize = min(bitmap.width, bitmap.height)
    val cropSize = (baseSize / zoom)
        .toInt()
        .coerceIn(1, baseSize)
    val startX = ((bitmap.width - cropSize) / 2).coerceAtLeast(0)
    val startY = ((bitmap.height - cropSize) / 2).coerceAtLeast(0)

    val cropped = Bitmap.createBitmap(
        bitmap,
        startX,
        startY,
        cropSize,
        cropSize
    )

    return Bitmap.createScaledBitmap(
        cropped,
        outputSize,
        outputSize,
        true
    )
}

private fun saveProfileBitmap(
    context: Context,
    bitmap: Bitmap
): Uri {
    val directory = File(context.filesDir, "user_profile").apply { mkdirs() }
    val file = File(directory, "profile_avatar.jpg")

    FileOutputStream(file).use { output ->
        check(bitmap.compress(Bitmap.CompressFormat.JPEG, 92, output)) {
            "ذخیره تصویر پروفایل انجام نشد."
        }
    }

    return Uri.fromFile(file)
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

private const val EDITOR_SOURCE_SIZE_PX = 1024
private const val PROFILE_OUTPUT_SIZE_PX = 512
