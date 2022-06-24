package com.ksnk.gif.ui.main

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.gif.enums.DisplayListType
import com.ksnk.gif.R
import com.ksnk.gif.data.empty.Gif
import com.ksnk.gif.models.GifsList
import com.ksnk.gif.ui.main.adapter.MainRecyclerViewAdapter


class MainActivity : AppCompatActivity() {
    private var mAdapter: MainRecyclerViewAdapter?=null
    private var mActivityViewModel: MainActivityViewModel?=null
    private var mRecyclerView: RecyclerView?=null
    private var mGridLayoutManager: GridLayoutManager?=null
    private var searchView: SearchView?=null
    private var changeDisplayTypeRadioGroup:RadioGroup?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initRecyclerView()
        performSearch()
        loadTrend()
    }

    private fun init() {
        mRecyclerView = findViewById(R.id.mainRecyclerView)
        searchView = findViewById(R.id.search_view)
        changeDisplayTypeRadioGroup = findViewById(R.id.changeDisplayTypeRadioGroup)
        changeDisplayTypeRadioGroup?.setOnCheckedChangeListener{ _, i ->
            when (i) {
                R.id.radioButtonList -> {
                    onChangeTypeDisplay(DisplayListType.List)
                }
                R.id.radioButtonGrid -> {
                    onChangeTypeDisplay(DisplayListType.Grid)
                }
            }
    }}

    private fun initRecyclerView() {
        mGridLayoutManager = GridLayoutManager(this, 1)
        mRecyclerView?.layoutManager = mGridLayoutManager
        mAdapter = MainRecyclerViewAdapter(this)
        mRecyclerView?.adapter = mAdapter
    }

    private fun performSearch() {
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }
        })
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun search(text: String?) {
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mAdapter?.setView(mActivityViewModel!!)
        mActivityViewModel?.getLiveDataObserver()?.observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    mAdapter?.setUpdatedGifs(t.data as ArrayList<Gif>)
                    mAdapter?.notifyDataSetChanged()
                    try {
                        mActivityViewModel?.insert(t.data)
                    } catch (e: SQLiteConstraintException) {
                        e.stackTrace

                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })

        mActivityViewModel?.retroFitResponseSearch(text.toString())
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadTrend() {
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mAdapter?.setView(mActivityViewModel!!)
        mActivityViewModel?.getLiveDataObserver()?.observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    mAdapter?.setUpdatedGifs(t.data as ArrayList<Gif>)
                    mAdapter?.notifyDataSetChanged()
                    try {
                        mActivityViewModel?.insert(t.data)
                    } catch (e: SQLiteConstraintException) {
                        e.stackTrace

                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })

        mActivityViewModel?.retroFitResponseTrend()
    }

    private fun onChangeTypeDisplay(displayListType: DisplayListType) {
        mGridLayoutManager?.spanCount = if (displayListType === DisplayListType.Grid) 3 else 1
        mAdapter?.setDisplayListType(displayListType)
        mRecyclerView?.layoutManager = mGridLayoutManager
        mAdapter?.notifyItemRangeChanged(0, mAdapter!!.itemCount)
    }
}
