package com.ksnk.gif.api

import com.ksnk.gif.models.GifsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("gifs/search")
    fun getDataFromAPI(
        @Query("api_key") apiKey: String,
        @Query("q") query: String
    ): Call<GifsList>
}