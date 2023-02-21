package com.theophiluskibet.caloryninja.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "calory_table")
data class CaloryEntity(
    val calories: Double,
    val carbohydrates_total_g: Double,
    val cholesterol_mg: Int,
    val fat_saturated_g: Double,
    val fat_total_g: Double,
    val fiber_g: Double,
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val potassium_mg: Int,
    val protein_g: Double,
    val serving_size_g: Double,
    val sodium_mg: Int,
    val sugar_g: Double
)
