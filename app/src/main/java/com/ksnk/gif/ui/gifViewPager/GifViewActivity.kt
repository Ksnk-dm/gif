package com.ksnk.gif.ui.gifViewPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.ksnk.gif.ui.gifViewPager.adapter.ImageViewPagerAdapter
import com.ksnk.gif.R
import com.ksnk.gif.data.empty.Gif
import java.util.ArrayList

class GifViewActivity : AppCompatActivity() {
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter
    private lateinit var viewPager2: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif_view)
        init()
        initViewPagerAndLoadExtras()
    }

    private fun init() {
        viewPager2 = findViewById(R.id.viewPager)
    }

    private fun initViewPagerAndLoadExtras() {
        val list: ArrayList<Gif>? =
            intent.getSerializableExtra(getString(R.string.intent_list)) as ArrayList<Gif>?
        val position: Int = intent.getIntExtra(getString(R.string.intent_position), 1)
        imageViewPagerAdapter = ImageViewPagerAdapter(list, this)
        viewPager2.adapter = imageViewPagerAdapter
        viewPager2.setCurrentItem(position, false)
    }
}