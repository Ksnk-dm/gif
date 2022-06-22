package com.ksnk.gif

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("original")
    @Expose
    var original: Original
) {
    override fun toString(): String {
        return "Images(original=$original)"
    }
}