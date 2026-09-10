package com.wstore.engine.ui.imei_warranty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel لایه نمایش Attributeهای IMEI و Warranty.
 *
 * UI به صورت مستقیم با Storage کار نمی‌کند.
 */
@HiltViewModel
class AttributeLookupViewModel @Inject constructor(
    private val repository: AttributeLookupRepository
) : ViewModel() {

    private val _values = MutableStateFlow<Map<String, String>>(emptyMap())
    val values: StateFlow<Map<String, String>> = _values

    fun load(attributeType: String) {
        viewModelScope.launch {
            _values.value = repository.load(attributeType)
        }
    }
}
