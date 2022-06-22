package com.ksnk.gif.di


import android.content.Context
import com.ksnk.gif.di.modules.DataBaseModule
import com.ksnk.gif.di.modules.RetroFitModule
import com.ksnk.gif.ui.main.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        RetroFitModule::class, DataBaseModule::class]
)

interface AppComponent {
    fun inject(mainActivityViewModel: MainActivityViewModel)

    fun database(context: Context)
}