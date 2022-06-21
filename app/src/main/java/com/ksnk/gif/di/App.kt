package com.ksnk.gif.di

import android.app.Application
import com.ksnk.gif.di.modules.RetroFitModule
import javax.inject.Inject

class App:Application() {

    private lateinit var retroComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        retroComponent = DaggerAppComponent.builder()
            .retroFitModule(RetroFitModule())
            .build()
    }

    fun getRetroComponent(): AppComponent {
        return retroComponent
    }
}