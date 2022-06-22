package com.ksnk.gif.data.repository

import com.ksnk.gif.Gif
import com.ksnk.gif.data.AppDataBase
import com.ksnk.gif.data.dao.GifsDao
import javax.inject.Inject

class GifsRepository @Inject constructor(val db: AppDataBase) {
    private var gifsDao: GifsDao? = db.getGifsDao()

    fun insert(gif: Gif) = gifsDao?.insert(gif)

    fun delete(gif: Gif)=gifsDao?.delete(gif)

    fun update(gif: Gif) = gifsDao?.update(gif)

    fun getAll(): List<Gif>? = gifsDao?.getAll()
}