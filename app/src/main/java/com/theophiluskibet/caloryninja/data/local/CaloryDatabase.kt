package com.theophiluskibet.caloryninja.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CaloryEntity::class], version = 1)
abstract class CaloryDatabase : RoomDatabase() {
    abstract fun caloryDao(): CaloryDao
}
