package com.ksnk.gif

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GifsList(
    @SerializedName("data")
    @Expose
    val data: List<Gif>
) {
}