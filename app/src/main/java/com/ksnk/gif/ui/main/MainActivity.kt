package com.ksnk.gif.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.gif.Gif
import com.ksnk.gif.GifsList
import com.ksnk.gif.R
import com.ksnk.gif.ui.main.adapter.MainRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: MainRecyclerViewAdapter
    private lateinit var mActivityViewModel: MainActivityViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mGridLayoutManager: GridLayoutManager
    var PAGE_SIZE = 5 //Number of items per page

    var PAge_NUM = 1 // page num with websevices providing accordingly

    var mIsLoading = false // fetching more data

    var queryTemp =""
    var count = 0

    var isMoreDataAvailable = true // more data still available

    var isLastPage: Boolean = false
    var isLoading: Boolean = true
    var loadmore = true
    var limit = 3
    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.mainRecyclerView)

        initRecyclerView()
      //  initViewModel("test")
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    Log.e("test","reached the last element of recyclerview");
                  var  visibleItemCount = mGridLayoutManager.getChildCount();
                   var totalItemCount = mGridLayoutManager.getItemCount();
                   var pastVisiblesItems = mGridLayoutManager.findFirstVisibleItemPosition()

                    if (isLoading) {

                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {

                            isLoading = false;
                            Log.d("dddd", "load $totalItemCount")
if(totalItemCount==limit){
    limit += 2
    isLoading=true
}
                            search(searchView.query.toString(), limit)

                        }
                    }
                }
            }})

        searchView = findViewById(R.id.search_view)
        performSearch()
    }

    private fun method() {
        loadmore = true
        Log.d("lllll", "load")
    }

    fun getMoreItems() {
        //after fetching your data assuming you have fetched list in your
        // recyclerview adapter assuming your recyclerview adapter is
        //rvAdapter

        isLoading = false
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mActivityViewModel.getLiveDataObserver().observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    Log.d("dddd0", t.data.size.toString())
                    mAdapter.addData(t.data as ArrayList<Gif>)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })

    }

    private fun initRecyclerView() {
        mGridLayoutManager = GridLayoutManager(this, 1)

        mRecyclerView.layoutManager = mGridLayoutManager
        mAdapter = MainRecyclerViewAdapter()
        mRecyclerView.adapter = mAdapter
    }

    private fun initViewModel(query: String) {
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mActivityViewModel.getLiveDataObserver().observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    Log.d("dddd0", t.data.size.toString())
                    mAdapter.setUpdatedGifs(t.data as ArrayList<Gif>)
                    mAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })
      //  mActivityViewModel.retroFitResponse(query)
    }

    private fun performSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query, 5)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText, 5)
                return true
            }
        })
    }

    private fun search(text: String?, limit:Int) {
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mActivityViewModel.getLiveDataObserver().observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    mAdapter.setUpdatedGifs(t.data as ArrayList<Gif>)
                    mAdapter.notifyItemChanged(t.data.size)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })

        mActivityViewModel.retroFitResponse(text.toString(), limit)
    }
}
