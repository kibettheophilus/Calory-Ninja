package com.theophiluskibet.caloryninja.data.datasource

import com.theophiluskibet.caloryninja.data.local.CaloryEntity

interface CaloryRepository {
    suspend fun getCalories(food: String): List<CaloryEntity>

    suspend fun getCalory(food: String): CaloryEntity
}
