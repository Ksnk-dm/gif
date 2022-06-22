package com.ksnk.gif.ui.main.adapter

import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.gif.R

class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var imageView:ImageView = itemView.findViewById(R.id.imageViewGif)
    var imageButtonDel:ImageButton = itemView.findViewById(R.id.imageButtonDelete)
    var textViewId:TextView = itemView.findViewById(R.id.textViewId)
}