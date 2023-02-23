package com.theophiluskibet.caloryninja.data.remote.models

import com.google.gson.annotations.SerializedName

data class CaloryResponse(
    @SerializedName("items")
    val caloryItemsResponse: List<CaloryItemResponse>
)
