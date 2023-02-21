package com.theophiluskibet.caloryninja.data.datasource

import android.util.Log
import com.theophiluskibet.caloryninja.data.remote.api.CaloryApi
import com.theophiluskibet.caloryninja.data.remote.models.Calory

class CaloryRepositoryImpl(private val caloryApi: CaloryApi) : CaloryRepository {
    override suspend fun getCalories(food: String): Calory {
        val response = caloryApi.getCalories(food).body()!!

        Log.d("CALORIES", "CALORIESRE: $response")
        return response
    }
}
