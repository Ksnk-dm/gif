package com.ksnk.gif

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.ksnk.gif.data.empty.Gif
import java.util.ArrayList

class GifViewActivity : AppCompatActivity() {
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_view)
        var list: ArrayList<Gif>? = intent.getSerializableExtra("list") as ArrayList<Gif>?
        Log.d("dddd", list?.size.toString())
        var pos: Int = intent.getIntExtra("position", 1)
        viewPager2 = findViewById(R.id.viewPager)
        imageViewPagerAdapter = ImageViewPagerAdapter(list, this)
        viewPager2.adapter = imageViewPagerAdapter
        viewPager2.setCurrentItem(pos, false)
    }
}