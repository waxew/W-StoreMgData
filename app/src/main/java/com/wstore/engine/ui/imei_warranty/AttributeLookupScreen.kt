package com.wstore.engine.ui.imei_warranty

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * صفحه نمایش Attributeهای اختصاصی مانند IMEI و Warranty.
 *
 * داده از ViewModel دریافت می‌شود و UI هیچ داده کسب‌وکاری ثابت نگهداری نمی‌کند.
 */
@Composable
fun AttributeLookupScreen(
    title: String,
    viewModel: AttributeLookupViewModel,
    modifier: Modifier = Modifier
) {
    val values by viewModel.values.collectAsState()

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
