package com.ksnk.gif

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Original(
    @SerializedName("url")
    @Expose
    var url: String
) {
}