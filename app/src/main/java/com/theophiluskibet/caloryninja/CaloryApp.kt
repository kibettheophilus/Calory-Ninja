package com.theophiluskibet.caloryninja

import android.app.Application
import com.theophiluskibet.caloryninja.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CaloryApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CaloryApp)
            modules(listOf(module))
        }
    }
}
