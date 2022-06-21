package com.ksnk.gif.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.gif.Gif
import com.ksnk.gif.GifsList
import com.ksnk.gif.R
import com.ksnk.gif.ui.main.adapter.MainRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: MainRecyclerViewAdapter
    private lateinit var mActivityViewModel: MainActivityViewModel
    private lateinit var mRecyclerView: RecyclerView
    private var matchedPeople: ArrayList<Gif> = arrayListOf()

    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.mainRecyclerView)
        initRecyclerView()
      //  initViewModel("test")

        searchView = findViewById(R.id.search_view)
        performSearch()
    }

    private fun initRecyclerView() {
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = MainRecyclerViewAdapter()
        mRecyclerView.adapter = mAdapter
    }

    private fun initViewModel(query: String) {
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mActivityViewModel.getLiveDataObserver().observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    mAdapter.setUpdatedGifs(t.data)
                    mAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })
        mActivityViewModel.retroFitResponse(query)
    }

    private fun performSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    private fun search(text: String?) {
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mActivityViewModel.getLiveDataObserver().observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    mAdapter.setUpdatedGifs(t.data)
                    mAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "error",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            })
        mActivityViewModel.retroFitResponse(text.toString())
    }
}
