package com.ksnk.gif.di.modules

import android.app.Application
import android.content.Context
import com.ksnk.gif.ApiInterface
import com.ksnk.gif.data.AppDataBase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RetroFitModule {


    private val baseURL = "https://api.giphy.com/v1/"
    @Singleton
    @Provides
    fun getRetroServiceInterface(retrofit: Retrofit):ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun getRetroFitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}