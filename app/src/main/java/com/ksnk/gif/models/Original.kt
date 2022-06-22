package com.ksnk.gif.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Original(
    @SerializedName("url")
    @Expose
    var url: String
): Serializable {
}