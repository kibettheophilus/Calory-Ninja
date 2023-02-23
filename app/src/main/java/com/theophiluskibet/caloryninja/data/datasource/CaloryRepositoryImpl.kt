package com.theophiluskibet.caloryninja.data.datasource

import android.util.Log
import com.theophiluskibet.caloryninja.data.local.CaloryDao
import com.theophiluskibet.caloryninja.data.local.CaloryEntity
import com.theophiluskibet.caloryninja.data.mappers.toEntity
import com.theophiluskibet.caloryninja.data.remote.api.CaloryApi

class CaloryRepositoryImpl(private val caloryApi: CaloryApi, private val caloryDao: CaloryDao) :
    CaloryRepository {
    override suspend fun getCalories(food: String): List<CaloryEntity> {
        val listOfFoods = food.replace("and", "").trim().split("  ")
        val calories = caloryDao.getCaloriesByNames(listOfFoods)
        Log.d("LOCALCALORIES", "$listOfFoods ${listOfFoods.size} ${calories.size} $calories")
        return if (calories.size == listOfFoods.size) {
            calories
        } else {
            val response = caloryApi.getCalories(food).body()!!
            caloryDao.saveCalory(response.caloryItemsResponse.map { it.toEntity() })
            response.caloryItemsResponse.map { it.toEntity() }
        }
    }

    override suspend fun getCalory(food: String): CaloryEntity {
        return caloryDao.getCalory(food)
    }

    override suspend fun getSavedCalories(): List<CaloryEntity> {
        return caloryDao.getCalories()
    }
}
