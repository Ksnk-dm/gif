package com.ksnk.gif.ui.main

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ksnk.gif.data.empty.Gif
import com.ksnk.gif.models.GifsList
import com.ksnk.gif.R
import com.ksnk.gif.ui.main.adapter.MainRecyclerViewAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: MainRecyclerViewAdapter
    private lateinit var mActivityViewModel: MainActivityViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mGridLayoutManager: GridLayoutManager
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        initRecyclerView()
        performSearch()
    }

    private fun init() {
        mRecyclerView = findViewById(R.id.mainRecyclerView)
        searchView = findViewById(R.id.search_view)
    }

    private fun initRecyclerView() {
        mGridLayoutManager = GridLayoutManager(this, 1)
        mRecyclerView.layoutManager = mGridLayoutManager
        mAdapter = MainRecyclerViewAdapter(this)
        mRecyclerView.adapter = mAdapter
    }

    private fun performSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query, 2)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText, 2)
                return true
            }
        })
    }


    private fun search(text: String?, limit: Int) {
        mActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mAdapter.setView(mActivityViewModel)
        mActivityViewModel.getLiveDataObserver().observe(this,
            Observer<GifsList> { t ->
                if (t != null) {
                    mAdapter.setUpdatedGifs(t.data as ArrayList<Gif>)
                    mAdapter.notifyDataSetChanged()
                    try {
                        mActivityViewModel.insert(t.data)
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

        mActivityViewModel.retroFitResponse(text.toString())
    }
}
