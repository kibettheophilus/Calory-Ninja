package com.theophiluskibet.caloryninja.data.mappers

import com.theophiluskibet.caloryninja.data.local.CaloryEntity
import com.theophiluskibet.caloryninja.data.remote.models.CaloryItemResponse

fun CaloryItemResponse.toEntity() = CaloryEntity(
    calories = calories,
    carbohydratesTotalGrams = carbohydrates_total_g,
    cholesterolMilliGrams = cholesterol_mg,
    fatSaturatedGrams = fat_saturated_g,
    fatTotalGrams = fat_total_g,
    fiberGrams = fiber_g,
    name = name,
    potassiumMilliGrams = potassium_mg,
    proteinGrams = protein_g,
    servingSizeGrams = serving_size_g,
    sodiumMilliGrams = sodium_mg,
    sugarGrams = sugar_g
)
