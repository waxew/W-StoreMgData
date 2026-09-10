package com.wstore.engine.ui.imei_warranty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * صفحه نمایش Attributeهای اختصاصی مانند IMEI و Warranty.
 *
 * این صفحه عمداً به صنعت خاص وابسته نیست و داده را از Dynamic Attribute Engine دریافت می‌کند.
 * در مرحله بعد ViewModel واقعی Repository به این UI متصل می‌شود.
 */
@Composable
fun AttributeLookupScreen(
    title: String,
    values: Map<String, String>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = title)

        values.forEach { (key, value) ->
            Card {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(text = key)
                    Text(text = value)
                }
            }
        }
    }
}
