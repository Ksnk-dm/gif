package com.ksnk.gif.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ksnk.gif.Gif
import com.ksnk.gif.GifsList
import com.ksnk.gif.R

class MainRecyclerViewAdapter() : RecyclerView.Adapter<MainViewHolder>() {

    private var listGifs: List<Gif>? = null

    fun setUpdatedGifs(listGifs: List<Gif>) {
        this.listGifs = listGifs
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return MainViewHolder(
            layoutInflater.inflate(
                R.layout.list_items,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        Glide.with(holder.imageView)
            .asGif()
            .load(listGifs?.get(position)?.images?.original?.url)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return listGifs?.size ?: 0
    }
}