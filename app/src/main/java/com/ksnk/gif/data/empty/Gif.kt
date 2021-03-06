package com.ksnk.gif.data.empty

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.ksnk.gif.models.Images
import java.io.Serializable

@Entity
data class Gif(
    @SerializedName("type")
    @Expose
    var type: String?,
    @Expose
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @NonNull
    var id: String,
    @SerializedName("url")
    @Expose
    var url: String?,
    @SerializedName("slug")
    var slug: String?,
    @SerializedName("bitly_gif_url")
    @Expose
    var bitlyGifUrl: String?,
    @SerializedName("bitly_url")
    @Expose
    var bitlyUrl: String?,
    @SerializedName("embed_url")
    @Expose
    var embedUrl: String?,
    @SerializedName("username")
    @Expose
    var userName: String?,
    @SerializedName("source")
    @Expose
    var sourse: String?,
    @SerializedName("title")
    @Expose
    var title: String?,
    @SerializedName("rating")
    @Expose
    var rating: String?,
    @SerializedName("content_url")
    @Expose
    var contentUrl: String?,
    @SerializedName("source_tld")
    @Expose
    var sourceTld: String?,
    @SerializedName("source_post_url")
    @Expose
    var sourcePostUrl: String?,
    @SerializedName("is_sticker")
    @Expose
    var sticker: Int?,
    @SerializedName("import_datetime")
    @Expose
    var importDatetime: String?,
    @SerializedName("trending_datetime")
    @Expose
    var trendingDatetime: String?,
    @Ignore
    var images: Images?,
    var delStatus: Boolean
) : Serializable {

    constructor() : this(
        null,
        "",
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        0,
        null,
        null,
        null,
        false
    )

    override fun toString(): String {
        return "Gif(type=$type, id='$id', url=$url, slug=$slug, bitlyGifUrl=$bitlyGifUrl, bitlyUrl=$bitlyUrl, embedUrl=$embedUrl, userName=$userName, sourse=$sourse, title=$title, rating=$rating, contentUrl=$contentUrl, sourceTld=$sourceTld, sourcePostUrl=$sourcePostUrl, sticker=$sticker, importDatetime=$importDatetime, trendingDatetime=$trendingDatetime, images=$images, delStatus=$delStatus)"
    }

}