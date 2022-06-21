package com.ksnk.gif

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("gifs/search")
    fun getDataFromAPI(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int
    ): Call<GifsList>
}