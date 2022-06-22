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
    var delStatus:Boolean
):Serializable {

    constructor() : this(null, "", null, null, null, null, null, null, null, null, null, null, null, null , 0, null ,null, null, false)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Gif

        if (type != other.type) return false
        if (id != other.id) return false
        if (url != other.url) return false
        if (slug != other.slug) return false
        if (bitlyGifUrl != other.bitlyGifUrl) return false
        if (bitlyUrl != other.bitlyUrl) return false
        if (embedUrl != other.embedUrl) return false
        if (userName != other.userName) return false
        if (sourse != other.sourse) return false
        if (title != other.title) return false
        if (rating != other.rating) return false
        if (contentUrl != other.contentUrl) return false
        if (sourceTld != other.sourceTld) return false
        if (sourcePostUrl != other.sourcePostUrl) return false
        if (sticker != other.sticker) return false
        if (importDatetime != other.importDatetime) return false
        if (trendingDatetime != other.trendingDatetime) return false
        if (images != other.images) return false
        if (delStatus != other.delStatus) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type?.hashCode() ?: 0
        result = 31 * result + id.hashCode()
        result = 31 * result + (url?.hashCode() ?: 0)
        result = 31 * result + (slug?.hashCode() ?: 0)
        result = 31 * result + (bitlyGifUrl?.hashCode() ?: 0)
        result = 31 * result + (bitlyUrl?.hashCode() ?: 0)
        result = 31 * result + (embedUrl?.hashCode() ?: 0)
        result = 31 * result + (userName?.hashCode() ?: 0)
        result = 31 * result + (sourse?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (rating?.hashCode() ?: 0)
        result = 31 * result + (contentUrl?.hashCode() ?: 0)
        result = 31 * result + (sourceTld?.hashCode() ?: 0)
        result = 31 * result + (sourcePostUrl?.hashCode() ?: 0)
        result = 31 * result + (sticker ?: 0)
        result = 31 * result + (importDatetime?.hashCode() ?: 0)
        result = 31 * result + (trendingDatetime?.hashCode() ?: 0)
        result = 31 * result + (images?.hashCode() ?: 0)
        result = 31 * result + delStatus.hashCode()
        return result
    }

    override fun toString(): String {
        return "Gif(type=$type, id='$id', url=$url, slug=$slug, bitlyGifUrl=$bitlyGifUrl, bitlyUrl=$bitlyUrl, embedUrl=$embedUrl, userName=$userName, sourse=$sourse, title=$title, rating=$rating, contentUrl=$contentUrl, sourceTld=$sourceTld, sourcePostUrl=$sourcePostUrl, sticker=$sticker, importDatetime=$importDatetime, trendingDatetime=$trendingDatetime, images=$images, delStatus=$delStatus)"
    }

}