package com.ksnk.gif.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Images(
    @SerializedName("original")
    @Expose
    var original: Original
): Serializable {
    override fun toString(): String {
        return "Images(original=$original)"
    }
}