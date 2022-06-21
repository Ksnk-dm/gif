package com.ksnk.gif.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ksnk.gif.ApiInterface
import com.ksnk.gif.GifsList
import com.ksnk.gif.di.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application): AndroidViewModel(application) {
    @Inject
    lateinit var apiInterface: ApiInterface

    private lateinit var liveDataList: MutableLiveData<GifsList>


    init {
        //here we need to init application.
        (application as App).getRetroComponent().inject(this)
        liveDataList = MutableLiveData()
    }


    fun getLiveDataObserver(): MutableLiveData<GifsList> {
        return liveDataList
    }

    fun retroFitResponse(query:String, limit:Int) {
        val call: Call<GifsList> = apiInterface.getDataFromAPI("xkKpGU3MZrDUdlc8w5SVmxsqBns5n77a", query, limit)
        call.enqueue(object : Callback<GifsList> {
            override fun onFailure(call: Call<GifsList>, t: Throwable) {
                liveDataList.postValue(null)
            }

            override fun onResponse(call: Call<GifsList>, response: Response<GifsList>) {
                if(response.isSuccessful) {
                    liveDataList.postValue(response.body())
                } else {
                    liveDataList.postValue(null)
                }
            }
        })
    }
}