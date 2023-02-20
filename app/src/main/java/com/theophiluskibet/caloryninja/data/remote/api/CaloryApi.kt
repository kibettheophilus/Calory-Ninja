package com.theophiluskibet.caloryninja.data.remote.api

import com.theophiluskibet.caloryninja.data.remote.models.Calory
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CaloryApi {
    @GET("nutrition")
    suspend fun getCalories(@Query("query") query: String = "rice"): Response<Calory>
}
