package com.ksnk.gif.di.modules

import android.app.Application
import android.content.Context
import com.ksnk.gif.Gif
import com.ksnk.gif.data.AppDataBase
import com.ksnk.gif.data.dao.GifsDao
import com.ksnk.gif.data.repository.GifsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideContext(application: Application?): Context? {
        return application
    }

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context?): AppDataBase {
        return AppDataBase.getDatabase(context)!!
    }

}