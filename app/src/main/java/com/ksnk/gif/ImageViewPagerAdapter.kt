package com.ksnk.gif

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.ksnk.gif.data.empty.Gif
import java.util.ArrayList

class ImageViewPagerAdapter(private val gifList: ArrayList<Gif>?, context: Context) :
    RecyclerView.Adapter<ImageViewHolder>() {
    private var context:Context?= null
    init {
        this.context=context
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)


        return ImageViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(context!!)
            .load(gifList?.get(position)?.images?.original?.url)
            .error(R.drawable.ic_launcher_foreground)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return gifList?.size ?: 0
    }

    fun setData(imageUrl: String) {


    }
}