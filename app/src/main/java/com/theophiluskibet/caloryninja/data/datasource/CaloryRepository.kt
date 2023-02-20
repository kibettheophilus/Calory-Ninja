package com.theophiluskibet.caloryninja.data.datasource

import com.theophiluskibet.caloryninja.data.remote.models.Calory

interface CaloryRepository {
    suspend fun getCalories(): Calory
}
