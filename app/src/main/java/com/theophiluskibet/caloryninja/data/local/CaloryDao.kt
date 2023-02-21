package com.theophiluskibet.caloryninja.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface CaloryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCalory(list: List<CaloryEntity>)
}
