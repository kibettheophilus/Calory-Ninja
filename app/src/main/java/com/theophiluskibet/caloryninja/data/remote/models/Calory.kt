package com.theophiluskibet.caloryninja.data.remote.models

import com.google.gson.annotations.SerializedName

data class Calory(
    @SerializedName("items")
    val caloryItems: List<CaloryItem>
)
