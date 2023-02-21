package com.theophiluskibet.caloryninja.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepository
import com.theophiluskibet.caloryninja.data.remote.models.Calory
import com.theophiluskibet.caloryninja.utils.UiState
import kotlinx.coroutines.launch

class CaloryViewModel(private val caloryRepository: CaloryRepository) : ViewModel() {

    private val _calories = MutableLiveData<UiState<Calory>>()
    val calories: LiveData<UiState<Calory>> = _calories

    init {
        getCalories()
    }

    private fun getCalories() {
        _calories.value = UiState.Loading()

        viewModelScope.launch {
            try {
                val result = caloryRepository.getCalories()
                _calories.value = UiState.Success(result)
                Log.d("CALORIES", "CALORIESVM: $result")
            } catch (e: Exception) {
                _calories.value = UiState.Error(e.localizedMessage)
            }
        }
    }
}
