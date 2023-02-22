package com.theophiluskibet.caloryninja.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepository
import com.theophiluskibet.caloryninja.data.local.CaloryEntity
import com.theophiluskibet.caloryninja.utils.UiState
import kotlinx.coroutines.launch

class CaloryDetailsViewModel(private val caloryRepository: CaloryRepository) : ViewModel() {
    private val _caloryState = MutableLiveData<UiState<CaloryEntity>>()
    val caloryState: LiveData<UiState<CaloryEntity>> = _caloryState

    fun getCalory(food: String) {
        _caloryState.value = UiState.Loading()
        viewModelScope.launch {
            try {
                val result = caloryRepository.getCalory(food)
                _caloryState.value = UiState.Success(result)
            } catch (e: Exception) {
                _caloryState.value = UiState.Error(e.localizedMessage)
            }
        }
    }
}
