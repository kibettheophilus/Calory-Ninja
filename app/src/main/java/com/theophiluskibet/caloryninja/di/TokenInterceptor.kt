package com.theophiluskibet.caloryninja.di

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder()
            .addHeader("X-Api-Key", "8/MqBej61B6ALLuEf7cIWg==tJbmaSTQHGZd6wLJ").build()
        return chain.proceed(request)
    }
}
