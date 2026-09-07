package com.wstore.engine.presentation.customer

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

// ViewModel بخش مشتریان
// وظیفه این کلاس اتصال UI به منطق Customer UseCase است.
// در این لایه هیچ منطق مربوط به Mobile، Beauty یا سایر کسب و کارها قرار نمی‌گیرد.

@HiltViewModel
class CustomerViewModel @Inject constructor() : ViewModel() {

    var state: CustomerUiState = CustomerUiState()
        private set

    // در مراحل بعدی CustomerUseCase به این ViewModel تزریق خواهد شد.
    fun loadCustomers() {
        state = state.copy(isLoading = true)
    }
}
