package com.ksnk.gif.data.dao

import androidx.room.*
import com.ksnk.gif.Gif

@Dao
interface GifsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg gif: Gif)

    @Delete
    fun delete(gif:Gif)

    @Query("DELETE FROM gif")
    fun deleteAll()

    @Update
    fun update(vararg gif: Gif)

    @Query("SELECT * FROM gif")
    fun getAll(): List<Gif>
}