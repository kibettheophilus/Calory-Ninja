package com.theophiluskibet.caloryninja.data.datasource

import android.util.Log
import com.theophiluskibet.caloryninja.data.remote.api.CaloryApi
import com.theophiluskibet.caloryninja.data.remote.models.Calory

class CaloryRepositoryImpl(private val caloryApi: CaloryApi) : CaloryRepository {
    override suspend fun getCalories(): Calory {
        val response = caloryApi.getCalories().body()!!

        Log.d("CALORIES", "CALORIESRE: $response")
        return response
    }
}
