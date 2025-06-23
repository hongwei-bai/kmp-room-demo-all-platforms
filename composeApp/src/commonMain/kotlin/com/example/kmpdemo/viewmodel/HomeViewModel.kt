package com.example.kmpdemo.viewmodel

import com.example.kmpdemo.database.DataRepository
import com.example.kmpdemo.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val dataRepository: DataRepository) : BaseViewModel() {
    private val _uiState = MutableStateFlow("")

    val uiState: StateFlow<String> = _uiState.asStateFlow()

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = dataRepository.getDataTest()
            _uiState.value = data.firstOrNull()?.name ?: "null"
        }
    }

    fun insertRecord() {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.insertDataTest("Orange")
            _uiState.value = "inserted data Orange"
        }
    }

    fun clearRecord() {
        viewModelScope.launch(Dispatchers.IO) {
            dataRepository.clearAllTest()
            _uiState.value = "all records cleared"
        }
    }
}