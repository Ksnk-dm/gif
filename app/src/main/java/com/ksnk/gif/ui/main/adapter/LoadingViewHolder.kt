package com.ksnk.gif.ui.main.adapter

import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.gif.R

class LoadingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    var progressBar:ProgressBar = itemView.findViewById(R.id.loadmore_progress)
}