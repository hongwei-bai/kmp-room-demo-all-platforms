package com.example.kmpdemo.ui.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class BaseViewModel {
    protected val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

    // Add shared cancellation logic if needed
    fun onCleared() {
        viewModelScope.coroutineContext.cancel()
    }
}
