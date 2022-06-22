package com.ksnk.gif.di

import android.app.Application
import com.ksnk.gif.di.modules.DataBaseModule
import com.ksnk.gif.di.modules.RetroFitModule

class App : Application() {

    private lateinit var appComponent: AppComponent


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .retroFitModule(RetroFitModule())
            .dataBaseModule(DataBaseModule(this))
            .build()
    }

    fun getRetroComponent(): AppComponent {
        return appComponent
    }
}