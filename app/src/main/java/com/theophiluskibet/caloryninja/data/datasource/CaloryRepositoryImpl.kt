package com.theophiluskibet.caloryninja.data.datasource

import com.theophiluskibet.caloryninja.data.remote.api.CaloryApi
import com.theophiluskibet.caloryninja.data.remote.models.Calory

class CaloryRepositoryImpl(private val caloryApi: CaloryApi) : CaloryRepository {
    override suspend fun getCalories(): Calory {
        return caloryApi.getCalories().body()!!
    }
}
