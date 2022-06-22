package com.ksnk.gif.data.dao

import androidx.room.*
import com.ksnk.gif.data.empty.Gif

@Dao
interface GifsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg gif: Gif)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertList(listGifs: List<Gif>)

    @Delete
    fun delete(gif: Gif)

    @Query("DELETE FROM gif")
    fun deleteAll()

    @Update
    fun update(vararg gif: Gif)

    @Query("SELECT * FROM gif")
    fun getAll(): List<Gif>

    @Query("SELECT * FROM gif WHERE id =:id")
    fun getId(id:String): Gif
}