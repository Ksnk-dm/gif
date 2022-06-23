package com.ksnk.gif.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ksnk.gif.api.ApiInterface
import com.ksnk.gif.data.empty.Gif
import com.ksnk.gif.models.GifsList
import com.ksnk.gif.data.AppDataBase
import com.ksnk.gif.data.repository.GifsRepository
import com.ksnk.gif.di.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private var apiKey = "xkKpGU3MZrDUdlc8w5SVmxsqBns5n77a"

    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var dataBase: AppDataBase

    @Inject
    lateinit var repository: GifsRepository
    private lateinit var liveDataList: MutableLiveData<GifsList>


    init {
        (application as App).getAppComponent().inject(this)
        liveDataList = MutableLiveData()
    }


    fun getLiveDataObserver(): MutableLiveData<GifsList> {
        return liveDataList
    }


    fun retroFitResponseSearch(query: String) {
        val call: Call<GifsList> =
            apiInterface.getDataSearchFromAPI(apiKey, query, 25)
        call.enqueue(object : Callback<GifsList> {
            override fun onFailure(call: Call<GifsList>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<GifsList>, response: Response<GifsList>) {
                if (response.isSuccessful) {
                    liveDataList.postValue(response.body())
                } else {
                    liveDataList.postValue(null)
                }
            }
        })
    }

    fun retroFitResponseTrend() {
        val call: Call<GifsList> =
            apiInterface.getDataTrendFromAPI(apiKey, "g", 25)
        call.enqueue(object : Callback<GifsList> {
            override fun onFailure(call: Call<GifsList>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<GifsList>, response: Response<GifsList>) {
                if (response.isSuccessful) {
                    liveDataList.postValue(response.body())
                } else {
                    liveDataList.postValue(null)
                }
            }
        })
    }

    fun insert(listGifs: List<Gif>) {
        repository.insertList(listGifs)
    }

    fun getAll(): List<Gif>? {
        return repository.getAll()
    }

    fun getId(id: String): Gif? {
        return repository.getId(id)
    }

    fun update(gif: Gif) {
        repository.update(gif)
    }
}