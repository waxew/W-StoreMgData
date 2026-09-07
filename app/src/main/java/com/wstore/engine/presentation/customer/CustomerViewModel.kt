package com.wstore.engine.presentation.customer

// ViewModel بخش مشتریان
// وظیفه این کلاس اتصال UI به منطق Customer UseCase است.
// در این لایه هیچ منطق مربوط به Mobile، Beauty یا سایر کسب و کارها قرار نمی‌گیرد.

class CustomerViewModel {

    var state: CustomerUiState = CustomerUiState()
        private set

    // در مراحل بعدی این بخش به CustomerUseCase متصل می‌شود.
    fun loadCustomers() {
        state = state.copy(isLoading = true)
    }
}
