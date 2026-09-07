package com.wstore.engine.ui.screens.product

/*
================================================
نام فایل:
ProductListScreen.kt

وظیفه:
صفحه نمایش لیست محصولات.

این صفحه عمومی است و نباید وابسته به نوع کسب و کار باشد.
اطلاعاتی مثل IMEI، رنگ، سایز یا تاریخ انقضا از طریق
Attribute Engine و Business Profile مدیریت می‌شوند.
================================================
*/

package com.wstore.engine.ui.screens.product

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductListScreen() {
    Text(text = "لیست محصولات")
}
