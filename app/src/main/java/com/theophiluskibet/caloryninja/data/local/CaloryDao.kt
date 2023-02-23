package com.theophiluskibet.caloryninja.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CaloryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCalories(list: List<CaloryEntity>)

    @Query("SELECT * FROM calory_table")
    suspend fun getCalories(): List<CaloryEntity>

    @Query("SELECT * FROM calory_table WHERE name=:name")
    suspend fun getCalory(name: String): CaloryEntity

    @Query("SELECT * FROM calory_table WHERE name IN(:names)")
    suspend fun getCaloriesByNames(names: List<String>): List<CaloryEntity>
}
