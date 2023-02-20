package com.theophiluskibet.caloryninja.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepository
import com.theophiluskibet.caloryninja.data.remote.models.Calory
import kotlinx.coroutines.launch

class CaloryViewModel(private val caloryRepository: CaloryRepository) : ViewModel() {

    private val _calories = MutableLiveData<Calory>()
    val calories: LiveData<Calory> = _calories

    init {
        getCalories()
    }

    private fun getCalories() {
        viewModelScope.launch {
            val result = caloryRepository.getCalories()
            _calories.value = result
            Log.d("CALORIES", "CALORIES: $result")
        }
    }
}
