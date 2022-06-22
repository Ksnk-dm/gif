package com.ksnk.gif.di.modules

import android.app.Application
import com.ksnk.gif.data.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule(var application: Application?) {

    @Provides
    @Singleton
    fun providesAppDatabase(): AppDataBase {
        return AppDataBase.getDatabase(application?.applicationContext)!!
    }

}