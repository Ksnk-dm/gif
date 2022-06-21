package com.ksnk.gif.ui.main.adapter


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ksnk.gif.Gif
import com.ksnk.gif.R
import java.util.*
import kotlin.collections.ArrayList


class MainRecyclerViewAdapter() : RecyclerView.Adapter<MainViewHolder>() {

    private var listGifs: ArrayList<Gif>? = null

    fun setUpdatedGifs(listGifs: ArrayList<Gif>) {
        this.listGifs = listGifs
    }

    init {
        listGifs = ArrayList<Gif>()
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
       Log.d("dddd size", listGifs?.size.toString())
        Glide.with(holder.imageView)
            .asGif()
            .load(listGifs?.get(position)?.images?.original?.url)
            .apply(RequestOptions.centerCropTransform())
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return listGifs?.size ?: 0
    }

    fun addData(listItems: ArrayList<Gif>) {
        val size = this.listGifs?.size
        this.listGifs?.addAll(listItems)
        Log.d("dddd", listGifs?.size.toString())
        val sizeNew = this.listGifs?.size
        if (sizeNew != null) {
            notifyItemRangeChanged(size!!, sizeNew)
        }
    }
}