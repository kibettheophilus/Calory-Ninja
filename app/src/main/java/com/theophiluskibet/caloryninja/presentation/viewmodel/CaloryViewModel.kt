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

    private val _caloriesState = MutableLiveData<UiState<List<CaloryEntity>>>()
    val caloriesState: LiveData<UiState<List<CaloryEntity>>> = _caloriesState

    fun getCalories(food: String) {
        _caloriesState.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val result = caloryRepository.getCalories(food)
                _caloriesState.value = UiState.Success(result)
                Log.d("CALORIES", "CALORIESVM: $result")
            } catch (e: Exception) {
                _caloriesState.value = UiState.Error(e.localizedMessage)
                Log.d("CALORIES", "CALORIESVMErr: ${e.localizedMessage}")
            }
        }
    }

    fun getSavedCalories() {
        _caloriesState.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val result = caloryRepository.getSavedCalories()
                _caloriesState.value = UiState.Success(result)
            } catch (e: Exception) {
                _caloriesState.value = UiState.Error(e.localizedMessage)
            }
        }
    }
}
