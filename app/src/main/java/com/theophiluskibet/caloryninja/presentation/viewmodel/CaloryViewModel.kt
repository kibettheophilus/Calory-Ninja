package com.theophiluskibet.caloryninja.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepository
import com.theophiluskibet.caloryninja.data.local.CaloryEntity
import com.theophiluskibet.caloryninja.utils.UiState
import kotlinx.coroutines.launch

class CaloryViewModel(private val caloryRepository: CaloryRepository) : ViewModel() {

    private val _calories = MutableLiveData<UiState<List<CaloryEntity>>>()
    val calories: LiveData<UiState<List<CaloryEntity>>> = _calories

    fun getCalories(food: String) {
        _calories.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val result = caloryRepository.getCalories(food)
                _calories.value = UiState.Success(result)
                Log.d("CALORIES", "CALORIESVM: $result")
            } catch (e: Exception) {
                _calories.value = UiState.Error(e.localizedMessage)
                Log.d("CALORIES", "CALORIESVMErr: ${e.localizedMessage}")
            }
        }
    }

    fun getSavedCalories() {
        _calories.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val result = caloryRepository.getSavedCalories()
                _calories.value = UiState.Success(result)
            } catch (e: Exception) {
                _calories.value = UiState.Error(e.localizedMessage)
            }
        }
    }
}
