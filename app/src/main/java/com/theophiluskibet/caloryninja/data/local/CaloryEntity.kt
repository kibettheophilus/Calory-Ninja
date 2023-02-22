package com.theophiluskibet.caloryninja.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calory_table")
data class CaloryEntity(
    val calories: Double,
    val carbohydratesTotalGrams: Double,
    val cholesterolMilliGrams: Int,
    val fatSaturatedGrams: Double,
    val fatTotalGrams: Double,
    val fiberGrams: Double,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val potassiumMilliGrams: Int,
    val proteinGrams: Double,
    val servingSizeGrams: Double,
    val sodiumMilliGrams: Int,
    val sugarGrams: Double
)
