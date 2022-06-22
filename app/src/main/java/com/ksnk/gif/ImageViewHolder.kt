package com.ksnk.gif

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ImageViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
    var imageView:ImageView = itemView.findViewById(R.id.imageViewItem)
}