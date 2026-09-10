package com.wstore.engine.ui.imei_warranty

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * ViewModel لایه نمایش Attributeهای IMEI و Warranty.
 *
 * UI به صورت مستقیم با Storage کار نمی‌کند.
 * اتصال Repository واقعی در مرحله بعد از این State انجام می‌شود.
 */
@HiltViewModel
class AttributeLookupViewModel @Inject constructor() : ViewModel() {

    private val _values = MutableStateFlow<Map<String, String>>(emptyMap())
    val values: StateFlow<Map<String, String>> = _values

    fun load(attributeType: String) {
        // آماده دریافت داده از Dynamic Attribute Repository.
        // در این مرحله هیچ داده ساختگی ثبت نمی‌شود.
        _values.value = emptyMap()
    }
}
