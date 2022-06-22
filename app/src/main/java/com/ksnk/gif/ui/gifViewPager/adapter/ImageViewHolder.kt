package com.ksnk.gif.ui.gifViewPager.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.gif.R

class ImageViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
    var imageView:ImageView = itemView.findViewById(R.id.imageViewItem)
}