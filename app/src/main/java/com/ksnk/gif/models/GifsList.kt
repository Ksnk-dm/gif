package com.ksnk.gif.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ksnk.gif.data.empty.Gif
import java.io.Serializable

data class GifsList(
    @SerializedName("data")
    @Expose
    val data: List<Gif>
):Serializable {
}