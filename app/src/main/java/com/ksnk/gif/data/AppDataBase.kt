package com.ksnk.gif.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ksnk.gif.data.empty.Gif
import com.ksnk.gif.data.dao.GifsDao

@Database(
    version = 1,
    exportSchema = false,
    entities = [(Gif::class)]
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getGifsDao(): GifsDao?

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null
        fun getDatabase(context: Context?): AppDataBase? {
            if (INSTANCE == null) {
                synchronized(AppDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context!!,
                            AppDataBase::class.java,
                            "GIF_DB"
                        ).allowMainThreadQueries()
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                }

                                override fun onOpen(db: SupportSQLiteDatabase) {
                                    super.onOpen(db)
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}