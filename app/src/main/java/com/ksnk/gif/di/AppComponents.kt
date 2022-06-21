package com.ksnk.gif.di

import com.ksnk.gif.di.modules.RetroFitModule
import com.ksnk.gif.ui.main.MainActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetroFitModule::class]
)

interface AppComponent {

    fun inject(mainActivityViewModel: MainActivityViewModel)


}