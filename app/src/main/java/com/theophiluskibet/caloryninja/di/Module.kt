package com.theophiluskibet.caloryninja.di

import androidx.room.Room
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepository
import com.theophiluskibet.caloryninja.data.datasource.CaloryRepositoryImpl
import com.theophiluskibet.caloryninja.data.local.CaloryDatabase
import com.theophiluskibet.caloryninja.data.remote.api.CaloryApi
import com.theophiluskibet.caloryninja.presentation.viewmodel.CaloryViewModel
import com.theophiluskibet.caloryninja.utils.TokenInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASER_URL = "https://api.calorieninjas.com/v1/"

val module = module {
    single { provideRetrofit(okHttpClient = get()) }
    single { provideOkhttpClient() }
    single<CaloryRepository> { CaloryRepositoryImpl(caloryApi = get()) }
    single {
        Room.databaseBuilder(androidApplication(), CaloryDatabase::class.java, "calory.db").build()
    }
    single { get<CaloryDatabase>().caloryDao() }
    viewModel { CaloryViewModel(caloryRepository = get()) }
}

private fun provideRetrofit(okHttpClient: OkHttpClient): CaloryApi {
    return Retrofit.Builder().baseUrl(BASER_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CaloryApi::class.java)
}

private fun provideOkhttpClient(): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(TokenInterceptor()).build()
}
